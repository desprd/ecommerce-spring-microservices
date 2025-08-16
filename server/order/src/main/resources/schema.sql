CREATE TABLE IF NOT EXISTS orders (
    order_id BIGSERIAL PRIMARY KEY,
    course_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    status VARCHAR(16) NOT NULL CHECK (status IN ('PENDING','PAID','CANCELLED')),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

