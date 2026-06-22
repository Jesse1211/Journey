#!/usr/bin/env node
// Parse AI_README.md's daily AI-study log into ai-timeline.json — same shape
// as timeline.json (from ALGO_README) so the portfolio can render it with the
// existing timeline view. Day lines: "- [x] Day N D/M/YYYY note". Non-list,
// non-blank prose lines between days are captured as milestones (the
// reflections like "学了扭头就忘…").
import { readFileSync, writeFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const REPO = join(dirname(fileURLToPath(import.meta.url)), "..");
const md = readFileSync(join(REPO, "AI_README.md"), "utf8");
const lines = md.split("\n");

const DAY = /^- \[x\] Day\s?([\d.]+)\s+(?:(\d{1,2}\/\d{1,2}\/\d{4})\s*)?(.*)$/;

const entries = [];
const milestones = [];
let started = false; // skip the header preamble before the first day

for (const line of lines) {
  const d = line.match(DAY);
  if (d) {
    started = true;
    entries.push({
      day: "Day" + d[1],
      dayNum: Number(d[1]),
      rawDate: d[2] || null,
      section: "AI",
      note: (d[3] || "").trim() || null,
    });
    continue;
  }
  const t = line.trim();
  // a prose reflection line that sits between day entries → milestone
  if (started && t && !t.startsWith("- [")) {
    milestones.push({
      afterDay: entries.length ? entries[entries.length - 1].day : null,
      text: t,
      section: "AI",
    });
  }
}

const timeline = {
  generatedBy: ".design/gen-ai-timeline.mjs",
  source: "AI_README.md",
  note: "Daily AI-study log. rawDate kept verbatim (D/M/YYYY).",
  count: entries.length,
  entries,
  milestones,
};

writeFileSync(join(REPO, "ai-timeline.json"), JSON.stringify(timeline, null, 2) + "\n");
console.log(`wrote ai-timeline.json: ${entries.length} days, ${milestones.length} milestones`);
