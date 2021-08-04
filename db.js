const {Client} = require('pg');

const pgclient = new Client({
    host: process.env.POSTGRES_HOST,
    port: process.env.POSTGRES_PORT,
    user: 'postgres',
    password: 'postgres',
    database: 'postgres'
});

pgclient.connect();

pgclient.query('CREATE DATABASE checkerdb', function(err) { // create user's db
    if (err)
        console.log('ignoring the error'); // ignore if the db is there
    pgclient.end(); // close the connection
});

const client = new Client({
    host: process.env.POSTGRES_HOST,
    port: process.env.POSTGRES_PORT,
    user: 'postgres',
    password: 'postgres',
    database: 'checkerdb'
});

const passwords = 'CREATE TABLE passwords(id BIGSERIAL PRIMARY KEY, password VARCHAR(50) NOT NULL)'
const login_and_passwords = 'CREATE TABLE login_and_passwords(id BIGSERIAL PRIMARY KEY, password VARCHAR(50) NOT NULL, login VARCHAR(50) NOT NULL)'

client.query(passwords, (err, res) => {
    if (err) throw err
});

client.query(login_and_passwords, (err, res) => {
    if (err) throw err
});