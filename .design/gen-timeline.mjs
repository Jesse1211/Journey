#!/usr/bin/env node
// Parse ALGO_README.md's daily training log into timeline.json (ADR-005).
// Honest: we capture what's written (day label, raw date string, note, the
// `## year` section it falls under, and milestone divider lines). We do NOT
// normalize the ambiguous date formats (2024 uses M/D/YYYY, 2025 uses D/M/YYYY)
// — we keep the raw string and a best-effort ISO only when unambiguous.
import { readFileSync, writeFileSync } from "node:fs";
import { join } from "node:path";

const REPO = new URL("..", import.meta.url).pathname.replace(/\/$/, "");
const md = readFileSync(join(REPO, "ALGO_README.md"), "utf8");
const lines = md.split("\n");

// date is optional — Day13 has a note but no date
const DAY = /^- \[x\] Day\s?([\d.]+)\s+(?:(\d{1,2}\/\d{1,2}\/\d{4})\s*)?(.*)$/;
const YEAR = /^##\s+(\d{4})\s*$/;
const DIVIDER = /^-{3,}\s*(.+?)\s*-*$/;

let section = null;
const entries = [];
const milestones = [];

for (let i = 0; i < lines.length; i++) {
  const line = lines[i];
  const y = line.match(YEAR);
  if (y) { section = y[1]; continue; }
  const d = line.match(DAY);
  if (d) {
    entries.push({
      day: "Day" + d[1],
      dayNum: Number(d[1]),
      rawDate: d[2] || null,
      section,
      note: (d[3] || "").trim() || null,
    });
    continue;
  }
  const dv = line.match(DIVIDER);
  if (dv && dv[1] && !/^Day/.test(dv[1])) {
    // a milestone / divider note (e.g. 致敬黑曼巴, 恭喜达到一百天)
    milestones.push({ afterDay: entries.length ? entries[entries.length - 1].day : null, text: dv[1], section });
  }
}

const timeline = {
  generatedBy: ".design/gen-timeline.mjs",
  source: "ALGO_README.md",
  note: "Daily LeetCode training log. rawDate kept verbatim (2024 entries are M/D/YYYY, 2025 entries are D/M/YYYY — not normalized to avoid misinterpretation).",
  count: entries.length,
  entries,
  milestones,
};

writeFileSync(join(REPO, "timeline.json"), JSON.stringify(timeline, null, 2) + "\n");
console.log(`wrote timeline.json: ${entries.length} day entries, ${milestones.length} milestones`);
