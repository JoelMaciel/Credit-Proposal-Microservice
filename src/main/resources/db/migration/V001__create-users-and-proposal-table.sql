CREATE TABLE USERS (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(30) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    cpf VARCHAR(15) UNIQUE NOT NULL,
    income NUMERIC(10,2) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE PROPOSAL (
    proposal_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount_requested NUMERIC(10,2) NOT NULL,
    payment_deadline INTEGER NOT NULL,
    approved BOOLEAN,
    integrated BOOLEAN,
    observation VARCHAR(200),
    user_id UUID REFERENCES USERS(user_id),
    date_proposal TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
