1. Users
- id
- email
- first_name
- last_name
- age
- city
- country
- password

2. Movies
- id
- title
- description
- duration
- age_rating
- rating

3. Documentations
- id
- name
- type
- file
- description
- movie_id
- spotlight_id

4. Actors
- id
- first_name
- last_name
- description

5. Movie_Actors
- movie_id
- actor_id

6. Genres
- id
- name
- description

7. Movie_Genres
- movie_id
- genre_id

8. Cinema_Brands
- id
- name
- description

9. Cinemas
- id
- name
- address
- city
- country
- cinema_brand_id

10. Studio_Shows
- id
- dimensions
- timetable
- total_seats
- cinema_id
- movie_id

11. Seats
- id
- studio_show_id
- user_id

12. Transactions
- id
- amount
- is_done
- user_id
- studio_show_id

13. Spotlights
- id
- title
- description
- created_at
