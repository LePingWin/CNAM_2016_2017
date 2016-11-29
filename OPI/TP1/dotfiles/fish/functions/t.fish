function t
    if math (count $argv) == 0 >/dev/null
        ~/lib/t/t.py --task-dir="~/Dropbox/tasks" --list=tasks.txt | sort -t - -k 2.1
    else
        ~/lib/t/t.py --task-dir="~/Dropbox/tasks" --list=tasks.txt $argv
    end
end

