DROP TABLE IF EXISTS course;    
DROP TABLE IF EXISTS author;

CREATE TABLE IF NOT EXISTS author (
    author_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    about TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS course (
    course_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(12, 2) NOT NULL,
    spots_left INTEGER NOT NULL,
    author_id BIGINT NOT NULL REFERENCES author(author_id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);
