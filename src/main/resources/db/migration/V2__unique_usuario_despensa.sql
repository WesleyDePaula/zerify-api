-- V2: Remove duplicate despensa rows per usuario and add unique constraint
-- IMPORTANT: Back up the database before running this migration.

-- 1) Optional: inspect duplicates
-- SELECT usuario_id, COUNT(*) AS qtd FROM despensa GROUP BY usuario_id HAVING COUNT(*) > 1;

-- 2) Remove duplicate despensa entries keeping the one with smallest id per usuario
WITH duplicates AS (
  SELECT id, usuario_id,
         ROW_NUMBER() OVER (PARTITION BY usuario_id ORDER BY id) AS rn
  FROM despensa
  WHERE usuario_id IS NOT NULL
)
DELETE FROM despensa
WHERE id IN (SELECT id FROM duplicates WHERE rn > 1);

-- 3) Add unique constraint on usuario_id to ensure one despensa per usuario
ALTER TABLE despensa
ADD CONSTRAINT uq_despensa_usuario UNIQUE (usuario_id);

