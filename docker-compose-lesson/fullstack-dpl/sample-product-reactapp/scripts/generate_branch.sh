#!/bin/bash 
# Git Workflow Branch Creator Script
# Supports: feature/*, bugfix/*, hotfix/*, release/*

set -e

# Check for git repository
if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  echo "❌ Not inside a Git repository. Aborting."
  exit 1
fi

# Define default base branches
MAIN_BRANCH="main"
DEVELOP_BRANCH="develop"

echo "Select branch type to create:"
select TYPE in feature bugfix hotfix release; do
    case $TYPE in
        feature)
            DEFAULT_BASE=$DEVELOP_BRANCH
            break
            ;;
        bugfix)
            DEFAULT_BASE=$DEVELOP_BRANCH
            break
            ;;
        hotfix)
            DEFAULT_BASE=$MAIN_BRANCH
            break
            ;;
        release)
            DEFAULT_BASE=$DEVELOP_BRANCH
            break
            ;;
        *)
            echo "Invalid selection. Please try again."
            ;;
    esac
done

# Prompt for branch name
read -p "Enter the name of your $TYPE (e.g., fix-login-issue): " BRANCH_NAME

# Sanitize branch name
BRANCH_NAME=$(echo "$BRANCH_NAME" | tr '[:upper:]' '[:lower:]' | tr ' ' '-' | tr -cd '[:alnum:]-')

# Final full branch name
FULL_BRANCH="$TYPE/$BRANCH_NAME"

# Fetch and checkout base branch
echo "📥 Fetching '$DEFAULT_BASE'..."
git fetch origin "$DEFAULT_BASE"
git checkout "$DEFAULT_BASE"
git pull origin "$DEFAULT_BASE"

# Create and push the new branch
echo "🌱 Creating branch '$FULL_BRANCH' from '$DEFAULT_BASE'..."
git checkout -b "$FULL_BRANCH"
git push -u origin "$FULL_BRANCH"

echo "✅ Branch '$FULL_BRANCH' created and pushed to origin."
