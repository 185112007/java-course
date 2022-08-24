## Reset/Restore

-- return to last commit

    git checkout <filename OR .>

-- cancel last change in stage

    git reset HEAD <file_name OR .>

-- OR

    git restore --staged <filename OR .>

-- cancel last commit

    git reset --hard HEAD~<1,2,3 - cancel number of last commit>

-- cancel last pushed commit

    git push -f origin <commit_id>:some_branch

## Managing Branches

-- create branch

    git branch <branch_name>

-- switch to another branch

    git checkout <branch_name>

-- create & switch to new branch

    git checkout -b <new_branch>

-- remove branch

    git checkout <another_branch>
    git branch -D <branch_name_for_delete>

## Pull changes from master branch

    git fetch -v
    git stash
    git checkut <main_branch>
    git pull
    git checkout <your_branch>
    git stash pop
    git merge <main_branch>
    git push