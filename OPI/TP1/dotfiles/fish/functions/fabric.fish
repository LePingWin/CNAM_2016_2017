function fabric -d "fab with my SSH identity"
    fab -i ~/.ssh/stevelosh $argv
end
