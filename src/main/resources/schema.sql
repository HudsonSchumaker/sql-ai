CREATE TABLE IF NOT EXISTS customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(16) NOT NULL,
    surname  VARCHAR(16) NOT NULL,
    email    VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL
);

CREATE INDEX idx_customer_email ON customer(email);


CREATE TABLE IF NOT EXISTS colleague (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT   NOT NULL,
    name    VARCHAR(16)  NOT NULL,
    surname VARCHAR(16)  NOT NULL,
    email   VARCHAR(32)  NOT NULL UNIQUE,
    type    VARCHAR(16)  NOT NULL,
    notes TEXT
);

-- colleague indexes
CREATE INDEX idx_colleague_customer_id ON colleague(customer_id);
CREATE INDEX idx_colleague_name ON colleague(name);

CREATE TABLE IF NOT EXISTS story (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    is_default BOOLEAN NOT NULL,
    title VARCHAR(32)  NOT NULL,
    description  TEXT,
    completed_at TIMESTAMP,
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP NOT NULL
);

-- story indexes
CREATE INDEX idx_story_customer_id ON story(customer_id);

CREATE TABLE IF NOT EXISTS task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id  BIGINT NOT NULL,
    story_id     BIGINT NOT NULL,
    assigned_to  BIGINT NOT NULL,
    title VARCHAR(32)   NOT NULL,
    description TEXT,
    priority INT NOT NULL,
    status   INT NOT NULL,
    due_date     TIMESTAMP,
    completed_at TIMESTAMP,
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP NOT NULL
);

-- task indexes
CREATE INDEX idx_task_customer_id ON task(customer_id);
CREATE INDEX idx_task_story_id ON task(story_id);
CREATE INDEX idx_task_assigned_to ON task(assigned_to);
CREATE INDEX idx_task_priority ON task(priority);
CREATE INDEX idx_task_status ON task(status);
