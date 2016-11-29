function ep -d "Edit .plan"
    nvim ~/.plan/README.markdown; and hg -R ~/.plan ci -m 'Update'
end
