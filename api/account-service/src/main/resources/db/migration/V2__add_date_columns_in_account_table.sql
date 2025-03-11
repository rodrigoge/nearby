ALTER TABLE account ADD COLUMN created_at TIMESTAMP WITH TIME ZONE;
ALTER TABLE account ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE;

UPDATE account SET created_at = NOW();
UPDATE account SET updated_at = NOW();
