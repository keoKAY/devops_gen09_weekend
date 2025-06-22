#!/bin/bash

# Script to create and push main Git branches to remote
# Includes: main, develop

set -e

BRANCHES=("main" "develop")

# Ensure we're inside a git repo
if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  echo "❌ Not inside a Git repository. Aborting."
  exit 1
fi

# Fetch from origin to avoid duplicate branches
git fetch origin

for BRANCH in "${BRANCHES[@]}"; do
  # Check if the branch exists locally
  if git show-ref --verify --quiet refs/heads/"$BRANCH"; then
    echo "🔁 Branch '$BRANCH' already exists locally."
  else
    echo "🌱 Creating branch '$BRANCH' locally..."
    git checkout --orphan "$BRANCH"
    git rm -rf . >/dev/null 2>&1 || true
    echo "# $BRANCH branch" > README.md
    git add README.md
    git commit -m "Initialize $BRANCH branch"
  fi

  # Push the branch to remote
  echo "🚀 Pushing '$BRANCH' to origin..."
  git push -u origin "$BRANCH"
done

# Checkout back to main or develop
git checkout main || git checkout develop

echo "✅ All base branches initialized and pushed to remote."
