#!/usr/bin/env node
// Migrate CodeBlog Hugo guides into Journey/guides/ (ADR-006).
// - TOML front-matter (+++ … +++) -> YAML (--- … ---)
// - {{< notice TYPE >}} … {{< /notice >}} -> blockquote with a bold label
// CodeBlog repo is READ ONLY here.
import { readFileSync, writeFileSync, mkdirSync, readdirSync, statSync } from "node:fs";
import { join } from "node:path";

const REPO = new URL("..", import.meta.url).pathname.replace(/\/$/, "");
const SRC = process.argv[2]; // CodeBlog/content/algorithm
const OUT = join(REPO, "guides");
mkdirSync(OUT, { recursive: true });

function findMd(dir) {
  const out = [];
  for (const n of readdirSync(dir)) {
    const p = join(dir, n);
    if (statSync(p).isDirectory()) out.push(...findMd(p));
    else if (n.endsWith(".md")) out.push(p);
  }
  return out;
}

function tomlToYaml(block) {
  // block is the inside of +++ … +++
  const out = [];
  for (const line of block.split("\n")) {
    const t = line.trim();
    if (!t) continue;
    let m;
    if ((m = t.match(/^(\w+)\s*=\s*\[(.*)\]$/))) {
      // array: authors = ["Jesse"]
      const items = m[2].split(",").map((s) => s.trim().replace(/^["']|["']$/g, "")).filter(Boolean);
      out.push(`${m[1]}:`);
      for (const it of items) out.push(`  - ${it}`);
    } else if ((m = t.match(/^(\w+)\s*=\s*"(.*)"$/))) {
      out.push(`${m[1]}: ${m[2]}`);
    } else if ((m = t.match(/^(\w+)\s*=\s*(.+)$/))) {
      out.push(`${m[1]}: ${m[2]}`);
    }
  }
  return out.join("\n");
}

function convertNotices(body) {
  // {{< notice TYPE >}} -> > **TYPE** \n (each following line prefixed with >)
  // {{< /notice >}} -> end
  const lines = body.split("\n");
  const out = [];
  let inNotice = false;
  for (const line of lines) {
    let m;
    if ((m = line.match(/{{<\s*notice\s+(\w+)\s*>}}/))) {
      inNotice = true;
      const label = m[1].charAt(0).toUpperCase() + m[1].slice(1);
      out.push(`> **${label}**`);
      out.push(">");
      continue;
    }
    if (/{{<\s*\/notice\s*>}}/.test(line)) {
      inNotice = false;
      continue;
    }
    if (inNotice) {
      out.push(line.trim() === "" ? ">" : "> " + line);
    } else {
      out.push(line);
    }
  }
  return out.join("\n");
}

const files = findMd(SRC).sort();
let n = 0;
const index = [];
for (const f of files) {
  const raw = readFileSync(f, "utf8");
  const fm = raw.match(/^\+\+\+\n([\s\S]*?)\n\+\+\+\n?([\s\S]*)$/);
  let yaml, body;
  if (fm) {
    yaml = tomlToYaml(fm[1]);
    body = fm[2];
  } else {
    yaml = "";
    body = raw;
  }
  body = convertNotices(body);
  // slug from filename
  const base = f.split("/").pop().replace(/\.md$/, "");
  const isPending = f.includes("/pending/");
  const front = ["---", yaml, isPending ? "status: pending" : "status: published", "source: CodeBlog", "---", "", ""].join("\n");
  const outPath = join(OUT, base + ".md");
  // strip a leading '---' hr from the body so it doesn't abut the front-matter fence
  writeFileSync(outPath, front + body.replace(/^\n+/, "").replace(/^---\n+/, ""));
  const titleMatch = yaml.match(/^title:\s*(.+)$/m);
  index.push({ slug: base, title: titleMatch ? titleMatch[1] : base, status: isPending ? "pending" : "published" });
  n++;
}
writeFileSync(join(OUT, "index.json"), JSON.stringify({ count: n, guides: index }, null, 2) + "\n");
console.log(`migrated ${n} guides into guides/`);
