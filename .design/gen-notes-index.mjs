#!/usr/bin/env node
// Build notes/index.json over the unified notes tree (Backend/Frontend/
// Courses/DevOps/MLs, incl. nested subdirs). Title is taken from YAML
// front-matter `title:` if present, else the first markdown heading, else
// the filename. Section = the top-level folder under notes/.
import { readFileSync, writeFileSync, readdirSync, statSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const REPO = join(dirname(fileURLToPath(import.meta.url)), "..");
const NOTES = join(REPO, "notes");

function walk(d) {
  const out = [];
  for (const n of readdirSync(d)) {
    if (n === ".DS_Store") continue;
    const p = join(d, n);
    if (statSync(p).isDirectory()) out.push(...walk(p));
    else if (n.endsWith(".md")) out.push(p);
  }
  return out;
}

function deriveTitle(src, fallback) {
  const fm = src.match(/^---\n([\s\S]*?)\n---/);
  if (fm) {
    const t = fm[1].match(/^title:\s*(.+)$/m);
    if (t) return t[1].trim();
  }
  const body = src.replace(/^---\n[\s\S]*?\n---\n?/, "");
  const h = body.match(/^#{1,6}\s+(.+)$/m);
  if (h) return h[1].trim();
  return fallback;
}

const posts = [];
for (const abs of walk(NOTES).sort()) {
  const rel = abs.slice(NOTES.length + 1).replace(/\\/g, "/"); // Backend/Java.md
  const section = rel.split("/")[0];
  const subPath = rel.split("/").slice(1, -1).join("/") || null; // Papers, Classes…
  const slug = rel.replace(/\.md$/, "");
  const src = readFileSync(abs, "utf8");
  const fileBase = rel.split("/").pop().replace(/\.md$/, "");
  posts.push({ slug, section, subPath, title: deriveTitle(src, fileBase) });
}

const sections = [...new Set(posts.map((p) => p.section))].sort();
writeFileSync(
  join(NOTES, "index.json"),
  JSON.stringify({ count: posts.length, sections, posts }, null, 2) + "\n",
);
console.log(`wrote notes/index.json: ${posts.length} posts across ${sections.length} sections (${sections.join(", ")})`);
