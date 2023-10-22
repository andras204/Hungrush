![dinkDonk](https://cdn.7tv.app/emote/611954ad501aa7dfff341cf6/4x.webp)

# BREAKING CHANGES

## The entire project has been redone, including the database. `git clone` the project again!

## Check `sql/` for the database changes!

# Hungrush

This is a group project for a methodology of programming class.

## Project setup guide

### Prerequisites

- Springboot plugin for Netbeans
	- goto https://github.com/AlexFalappa/nb-springboot and follow the installation instructions provided there
- MySql Connector Java
	- goto https://dev.mysql.com/downloads/connector/j/
	- select 'Platform independent' for operating system
	- download and extract the zip
	- Making a new DB connection in Netbeans, using the extracted msql-connector-j.jar file
- hungrush database in xampp
	- import `sql/hungrush.sql` into xampp
	- don't forget to actually start xampp

#### Clone the repository

```
git clone https://github.com/andras204/Hungrush
```

## Contributing

**The `main` branch always has to remain functional!**

To achieve this, create a new branch

```
git checkout -b <branch-name>
```

Then commit and push all your changes to this new branch.

First, add the files you created or changed

```
git add file.extension
git add directory
```

Then commit the changes

```
git commit -m "<commit message>"
```

And finally push the changes to the remote repository

```
git push
```

After that submit a pull request(PR) and if your changes didn't break anything we'll merge it into the `main` branch.

For a more comprehesive guide read [this article](https://medium.com/@jonathanmines/the-ultimate-github-collaboration-guide-df816e98fb67).
