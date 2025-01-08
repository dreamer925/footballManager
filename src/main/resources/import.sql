-- Insert Teams
INSERT INTO team_model (id, name, fee, account) VALUES (1, 'Manchester United', 5.00, 3000000.00),(2, 'Real Madrid', 2.00, 5675000.00),(3, 'Bayern Munich', 1.80, 400000.00);

-- Insert Players
INSERT INTO player_model (id, name, surname, age, months_of_experience, team_id) VALUES (1, 'Marcus', 'Rashford', 25, 84, 1),(2, 'Bruno', 'Fernandes', 28, 108, 1),(3, 'Vinícius', 'Júnior', 23, 60, 2),(4, 'Luka', 'Modric', 38, 240, 2),(5, 'Joshua', 'Kimmich', 28, 120, 3),(6, 'Thomas', 'Müller', 34, 180, 3);

UPDATE team_model_seq SET next_val = 4;
UPDATE player_model_seq SET next_val = 7;
