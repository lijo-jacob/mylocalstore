# mylocalstore
An ecommerce store built using opensource frameworks


Installation Instructions

brew update

brew install mongodb

cd ~/github/mylocalstore
mkdir data

mongod --dbpath ./data

mongo
> use mls
> exit

mvn spring-boot:run
