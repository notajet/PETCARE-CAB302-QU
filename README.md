# PETCARE-CAB302-QU

## Git Cheat Sheet

### how to push a change to github (see below how to do this with branching which is the reccomended method):

### 1) git add . 
This adds all the files that have been editted

### 2) git commit -m "</message/>" 
this commits the files you added with a message 

### 3) git push
final step to send all your changes to github

### Branching (Very Important)
we need to branch so that we're all not working on the main and mess everything up.

### git branch
this lists out all the branches

### git branch </branch/>
create a new branch with name </branch/>

### git chekout </branch/>
go to branch with name </branch/> 


### how to merge your branch to main and push:
### Make sure you go into your branch first 
### 1) git checkout </my branch name/> 
### 2) git pull origin main 
#### (do this when you go into your branch to pull changes made in main, it's good practice to do this even if nothing has changed as a double check) 
### After you're done coding in your branch: 
### 3) git add .
### 4) git commit -m "message" 
### 5) git checkout main (this takes you back to main)
### 6) git pull origin main 
### 7) git merge </branch name/>
### 8) git push 




