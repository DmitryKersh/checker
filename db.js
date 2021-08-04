const {Client} = require('pg');

const pgclient = new Client({
    host: process.env.POSTGRES_HOST,
    port: process.env.POSTGRES_PORT,
    user: 'postgres',
    password: 'postgres',
    database: 'checkerdb'
});

pgclient.connect();

const passwords = 'CREATE TABLE passwords(id BIGSERIAL PRIMARY KEY, password VARCHAR(50) NOT NULL)'
const login_and_passwords = 'CREATE TABLE login_and_passwords(id BIGSERIAL PRIMARY KEY, password VARCHAR(50) NOT NULL, login VARCHAR(50) NOT NULL)'

pgclient.query(passwords, (err, res) => {
    if (err) throw err
});

pgclient.query(login_and_passwords, (err, res) => {
    if (err) throw err
});