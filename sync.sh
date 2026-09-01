#!/bin/bash
# Auto Sync Script for Termux <-> GitHub Arena Agent

BRANCH="arena/01a05e97-ranu-meena"

echo "=========================================="
echo "      AUTOMATIC GIT SYNC FOR TERMUX       "
echo "=========================================="

if [ "$1" == "pull" ]; then
    echo "⬇️ Pulling latest updates from Arena Agent..."
    git pull origin $BRANCH
    echo "✅ Pull complete! Local files updated."
elif [ "$1" == "push" ]; then
    echo "⬆️ Pushing local files to GitHub Arena Agent..."
    git add .
    git commit -m "Auto sync from mobile Termux: $(date)"
    git push origin $BRANCH
    echo "✅ Push complete! Files sent to Arena Agent."
else
    echo "🔄 Running Full Sync (Pull then Push)..."
    git pull origin $BRANCH --no-rebase
    git add .
    git commit -m "Auto sync from mobile Termux: $(date)"
    git push origin $BRANCH
    echo "✅ Full Sync complete!"
fi
