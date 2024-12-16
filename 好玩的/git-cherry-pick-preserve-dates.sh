# GIT_COMMITTER_NAME="Jesse1211" GIT_COMMITTER_EMAIL="zl942@cornell.edu" GIT_COMMITTER_DATE="Fri Oct 18 03:24:10 2024 -0400" git commit --amend --no-edit --date="Fri Oct 18 03:24:10 2024 -0400" --author="Jesse1211 <zl942@cornell.edu>"


#!/usr/bin/env bash
set -e

# Example usage:
# ./cherry-pick-with-original-metadata.sh COMMIT_HASH_1 COMMIT_HASH_2 ...

for COMMIT_HASH in "$@"; do
  # Start cherry-picking without committing
  git cherry-pick -n "$COMMIT_HASH" || {
    echo "Cherry-pick of commit ${COMMIT_HASH} failed due to conflicts."
    echo "Please resolve conflicts, then run the script again."
    exit 1
  }

  # Retrieve original author and committer info
  AUTHOR_NAME=$(git show -s --format="%an" "$COMMIT_HASH")
  AUTHOR_EMAIL=$(git show -s --format="%ae" "$COMMIT_HASH")
  AUTHOR_DATE=$(git show -s --format="%aD" "$COMMIT_HASH")

  COMMITTER_NAME=$(git show -s --format="%cn" "$COMMIT_HASH")
  COMMITTER_EMAIL=$(git show -s --format="%ce" "$COMMIT_HASH")
  COMMIT_DATE=$(git show -s --format="%cD" "$COMMIT_HASH")

  # Retrieve the original commit message
  COMMIT_MESSAGE=$(git log -1 --pretty=%B "$COMMIT_HASH")

  # Export environment variables to preserve original metadata
  export GIT_AUTHOR_NAME="$AUTHOR_NAME"
  export GIT_AUTHOR_EMAIL="$AUTHOR_EMAIL"
  export GIT_AUTHOR_DATE="$AUTHOR_DATE"

  export GIT_COMMITTER_NAME="$COMMITTER_NAME"
  export GIT_COMMITTER_EMAIL="$COMMITTER_EMAIL"
  export GIT_COMMITTER_DATE="$COMMIT_DATE"

  # Commit the changes with the original commit message and metadata
  git commit -m "$COMMIT_MESSAGE"

  echo "Cherry-pick of commit ${COMMIT_HASH} completed with original author, committer, and dates preserved."
done

echo "All specified commits have been processed."

