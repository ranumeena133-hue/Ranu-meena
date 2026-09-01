while true; do echo "Syncing Data..."; git pull origin arena/01a05e97-ranu-meena; git add .; git commit -m "Auto-sync update"; git push origin arena/01a05e97-ranu-meena; sleep 30; done
