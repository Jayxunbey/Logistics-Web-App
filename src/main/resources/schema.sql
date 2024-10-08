insert into public.region (name_en)
values ('Yangiaryk'),
       ('Yangibozor'),
       ('Urganch'),
       ('Qarshi'),
       ('Turkman'),
       ('Moskva'),
       ('Quqan'),
       ('Istanbul'),
       ('Ashhabad'),
       ('Termiz'),
       ('Namangan'),
       ('Andijon'),
       ('Astana'),
       ('Tehron'),
       ('Bagdad'),
       ('Islamabad'),
       ('Pekin'),
       ('Ulan Batar'),
       ('Tokio');

INSERT INTO public.road_between_region (id, from_address_id, to_address_id, active)
VALUES (DEFAULT, 1, 2, true),
       (DEFAULT, 4, 5, true),
       (DEFAULT, 4, 12, false),
       (DEFAULT, 6, 8, false),
       (DEFAULT, 4, 14, true),
       (DEFAULT, 14, 16, true),
       (DEFAULT, 14, 7, false),
       (DEFAULT, 7, 19, true),
       (DEFAULT, 11, 13, false),
       (DEFAULT, 8, 10, true),
       (DEFAULT, 5, 9, true),
       (DEFAULT, 2, 9, true),
       (DEFAULT, 12, 14, false),
       (DEFAULT, 12, 18, true),
       (DEFAULT, 8, 17, true),
       (DEFAULT, 19, 15, false),
       (DEFAULT, 15, 7, true),
       (DEFAULT, 15, 18, false),
       (DEFAULT, 19, 2, true),
       (DEFAULT, 20, 5, false),
       (DEFAULT, 12, 11, true),
       (DEFAULT, 5, 17, true),
       (DEFAULT, 6, 19, false)


select region.id, region.name_en
from region
         inner join road_between_region on region.id = road_between_region.from_address_id or
                                           region.id = road_between_region.to_address_id group by region.id

