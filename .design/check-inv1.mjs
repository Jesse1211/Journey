// INV-1: every .java that declares a package must have it match its folder path.
// Convention in this repo: package = the LEAF folder name (matches existing files
// like `package 同向;`→`package SameDirection;`, `package BinarySearchInclusive;`).
// Files with no package declaration are fine (52 such originally).
import { readFileSync, readdirSync, statSync } from "node:fs";
import { join, relative } from "node:path";

const REPO = process.argv[2];
const ALGOS = join(REPO, "Algos");

function walk(d) {
  const out = [];
  for (const n of readdirSync(d)) {
    const p = join(d, n);
    if (statSync(p).isDirectory()) out.push(...walk(p));
    else if (n.endsWith(".java")) out.push(p);
  }
  return out;
}

let mismatches = 0, withPkg = 0, noPkg = 0;
for (const abs of walk(ALGOS)) {
  const src = readFileSync(abs, "utf8");
  const m = src.match(/^package\s+([^\s;]+)\s*;/m);
  const rel = relative(REPO, abs);
  const parts = rel.split("/"); // Algos/<topic>/.../file.java
  const leaf = parts[parts.length - 2]; // immediate folder
  if (!m) { noPkg++; continue; }
  withPkg++;
  const pkg = m[1];
  // accept leaf match OR dotted-path ending in leaf (e.g. BFS.Tree for folder Tree)
  const last = pkg.split(".").pop();
  if (last !== leaf) {
    console.log(`MISMATCH ${rel}: package '${pkg}' (leaf '${last}') vs folder '${leaf}'`);
    mismatches++;
  }
}
console.log(`\nfiles with package: ${withPkg}, without: ${noPkg}, mismatches: ${mismatches}`);
console.log(mismatches === 0 ? "INV-1 OK" : "INV-1 FAILED");
process.exit(mismatches === 0 ? 0 : 1);
