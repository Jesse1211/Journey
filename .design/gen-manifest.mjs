#!/usr/bin/env node
// Generates journey.json — the solution manifest. Re-runnable (ADR-007).
// One entry PER SOLUTION FILE (not per problem); duplicate ids kept (ADR-008).
// Fields are honest/derived only (ADR-002): id, topic, subtopic, nameFragment,
// solutionType, file, originalFile?, strategyComment?.
import { readFileSync, writeFileSync, readdirSync, statSync } from "node:fs";
import { join, relative } from "node:path";

const REPO = new URL("..", import.meta.url).pathname.replace(/\/$/, "");
const ALGOS = join(REPO, "Algos");

// originalFile lookup from the relocation log
const reloc = {};
try {
  const lines = readFileSync(join(REPO, ".design/relocations-applied.tsv"), "utf8")
    .trim().split("\n").slice(1);
  for (const l of lines) {
    const [orig, nw] = l.split("\t");
    reloc[nw] = orig; // newFile -> originalFile
  }
} catch {}

function walk(dir) {
  const out = [];
  for (const name of readdirSync(dir)) {
    const p = join(dir, name);
    const s = statSync(p);
    if (s.isDirectory()) out.push(...walk(p));
    else if (name.endsWith(".java")) out.push(p);
  }
  return out;
}

// Extract the leading strategy comment: the first /* ... */ block or run of //
// lines appearing before the class declaration. Returns trimmed text or null.
function strategyComment(src) {
  // drop a leading package/import preamble for scanning, but keep order
  const lines = src.split("\n");
  const collected = [];
  let inBlock = false;
  for (let raw of lines) {
    const line = raw.trim();
    if (!inBlock) {
      if (line.startsWith("/*")) {
        inBlock = true;
        const after = line.replace(/^\/\*+/, "").replace(/\*\/$/, "");
        if (after.trim()) collected.push(after.trim());
        if (line.endsWith("*/")) inBlock = false;
        continue;
      }
      if (line.startsWith("//")) {
        collected.push(line.replace(/^\/\/+/, "").trim());
        continue;
      }
      if (line === "" || line.startsWith("package ") || line.startsWith("import ")) continue;
      // hit real code (class/...) — stop
      break;
    } else {
      if (line.endsWith("*/")) {
        const body = line.replace(/\*\/$/, "").replace(/^\*+/, "").trim();
        if (body) collected.push(body);
        inBlock = false;
        continue;
      }
      collected.push(line.replace(/^\*+/, "").trim());
    }
  }
  const text = collected.filter(Boolean).join("\n").trim();
  return text || null;
}

const files = walk(ALGOS).sort();
const entries = [];
for (const abs of files) {
  const file = relative(REPO, abs); // e.g. Algos/BFS/Tree/104.java
  const parts = file.split("/"); // [Algos, topic, ...maybe subtopic, fname]
  const topic = parts[1];
  const fname = parts[parts.length - 1];
  const subtopic = parts.length > 3 ? parts.slice(2, -1).join("/") : null;
  const m = fname.match(/^(\d+)(.*)\.java$/);
  const id = m ? Number(m[1]) : null;
  const nameFragment = m && m[2] ? m[2] : null;
  const src = readFileSync(abs, "utf8");
  const entry = {
    id,
    topic,
    solutionType: topic, // type == corrected topic folder (ADR-009)
    file,
  };
  if (subtopic) entry.subtopic = subtopic;
  if (nameFragment) entry.nameFragment = nameFragment;
  if (reloc[file]) entry.originalFile = reloc[file];
  const sc = strategyComment(src);
  if (sc) entry.strategyComment = sc;
  entries.push(entry);
}

// stable sort: topic, then id, then file
entries.sort((a, b) =>
  a.topic.localeCompare(b.topic) ||
  (a.id ?? 0) - (b.id ?? 0) ||
  a.file.localeCompare(b.file)
);

const manifest = {
  generatedBy: ".design/gen-manifest.mjs",
  schema: {
    note: "One entry per solution FILE. Duplicate ids across topics are intentional (different solutions). Metadata is derived only — no invented difficulty/title/url.",
    fields: ["id", "topic", "solutionType", "subtopic?", "nameFragment?", "file", "originalFile?", "strategyComment?"],
  },
  count: entries.length,
  topics: [...new Set(entries.map((e) => e.topic))].sort(),
  solutions: entries,
};

writeFileSync(join(REPO, "journey.json"), JSON.stringify(manifest, null, 2) + "\n");
console.log(`wrote journey.json: ${entries.length} solutions across ${manifest.topics.length} topics`);
