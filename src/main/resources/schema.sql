

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
       (DEFAULT, 6, 19, false);


select region.id, region.name_en
from region
         inner join road_between_region on region.id = road_between_region.from_address_id or
                                           region.id = road_between_region.to_address_id group by region.id


select *
from road_transport rt
         inner join public.road_between_region rbr on rt.road_id = rbr.id
where checkIsValidTransportOptions(10, 8, false, rt, rbr) = true

/*/////////////////////////////////////////////////////////////////////*/
CREATE OR REPLACE FUNCTION findTransportOptions(fromId INT, toId INT, isBilateral boolean, rt road_transport,
                                                rbr road_between_region)
    RETURNS boolean AS
$$
BEGIN
    IF rt.is_bilateral = true THEN
        begin
            if (rbr.from_address_id = fromId and rbr.to_address_id = toId) or
               (rbr.from_address_id = toId and rbr.to_address_id = fromId) then
                return true;
            else
                return false;
            end if;
        end;
    else
        begin
            if isBilateral then
                return false;
            end if;

            if rt.is_directional then
                if rbr.from_address_id = fromId and rbr.to_address_id = toId then
                    return true;
                else
                    return false;
                end if;
            else
                if rbr.from_address_id = toId and rbr.to_address_id = fromId then
                    return true;
                else
                    return false;
                end if;
            end if;
        end;
    end if;

END;
$$ LANGUAGE plpgsql;



select *
from road_transport rt
         inner join public.road_between_region rbr on rt.road_id = rbr.id
where findTransportOptions(10, 8, false, rt, rbr) = true








 */
 */

--  Connection larni topish
WITH RECURSIVE RoutePaths AS (
    -- Boshlang'ich nuqtani topish
    SELECT
        rbr.id,
        rbr.from_address_id,
        rbr.to_address_id,
        ARRAY[rbr.id] as rbr_sequence,
        ARRAY[rbr.from_address_id, rbr.to_address_id] AS city_sequence -- Shaharning ketma-ketligi
    FROM road_between_region rbr
    WHERE rbr.from_address_id = 30 or rbr.to_address_id = 30 -- Boshlanish nuqtasi, City A (1)

    UNION ALL

    -- keyingi yo'llarni har qadamda qidirish
    SELECT
        r.id,
        r.from_address_id,
        r.to_address_id,
        rp.rbr_sequence || r.id,
        rp.city_sequence || r.to_address_id  -- Ketma-ket shaharlarni qo'shish
    FROM road_between_region r
             JOIN RoutePaths rp ON r.from_address_id = rp.to_address_id -- Bog'lanish
    WHERE r.to_address_id <> ALL(rp.city_sequence)  -- Davriy zanjirdan qochish (takrorlanmasin)
)

-- Topilgan yo'nalishlarni ko'rsatish
SELECT rbr_sequence, city_sequence FROM RoutePaths
where to_address_id = 3 or from_address_id = 3;


-- //////////////////////////////////////////////////  getAsRegionName
CREATE OR REPLACE FUNCTION getAsRegionName(arr INT[])
    RETURNS TEXT[] AS
$$
DECLARE
    total INTEGER := 0;
    element INTEGER;
    nameEn TEXT;
    result TEXT[] := '{}';
BEGIN
    -- Massivni bosqichma-bosqich bosib o'tish
    FOREACH element IN ARRAY arr
        LOOP
            select r.name_en into nameEn from region r where r.id = element;

            result := array_append(result, nameEn);

        END LOOP;

    RETURN result;
END;
$$ LANGUAGE plpgsql;

-- /////////////////////////////////////////// Finding Path

WITH RECURSIVE RoutePaths AS (
    -- Boshlang'ich nuqtani topish
    SELECT
        rbr.id,
        rbr.from_address_id,
        rbr.to_address_id,
        ARRAY[rbr.id] as rbr_sequence,
        ARRAY[rbr.from_address_id, rbr.to_address_id] AS city_sequence -- Shaharning ketma-ketligi
    FROM road_between_region rbr
    WHERE rbr.from_address_id = 31 -- Boshlanish nuqtasi, City A (1)

    UNION ALL

    -- keyingi yo'llarni har qadamda qidirish
    SELECT
        r.id,
        r.from_address_id,
        r.to_address_id,
        rp.rbr_sequence || r.id,
        rp.city_sequence || r.to_address_id  -- Ketma-ket shaharlarni qo'shish
    FROM road_between_region r
             JOIN RoutePaths rp ON r.from_address_id = rp.to_address_id -- Bog'lanish
    WHERE r.to_address_id <> ALL(rp.city_sequence)  -- Davriy zanjirdan qochish (takrorlanmasin)
)

-- Topilgan yo'nalishlarni ko'rsatish
SELECT rbr_sequence, city_sequence, getAsRegionName(city_sequence) as city_name_sequence FROM RoutePaths
where to_address_id = 32 ;





-- /////////////////////////////////////////  today 6
WITH RECURSIVE RoutePaths AS (
    -- Boshlang'ich nuqtani topish
    SELECT
        rbr.id,
        rbr.from_address_id,
        rbr.to_address_id,
        ARRAY[rbr.id] as rbr_sequence,
        ARRAY[rbr.from_address_id, rbr.to_address_id] AS city_sequence -- Shaharning ketma-ketligi
    FROM road_between_region rbr
    WHERE rbr.from_address_id = 31 -- Boshlanish nuqtasi, City A (1)

    UNION ALL

    -- keyingi yo'llarni har qadamda qidirish
    SELECT
        r.id,
        r.from_address_id,
        r.to_address_id,
        rp.rbr_sequence || r.id,
        rp.city_sequence || r.to_address_id  -- Ketma-ket shaharlarni qo'shish
    FROM road_between_region r
             JOIN RoutePaths rp ON r.from_address_id = rp.to_address_id -- Bog'lanish
    WHERE r.to_address_id <> ALL(rp.city_sequence)  -- Davriy zanjirdan qochish (takrorlanmasin)
)

-- Topilgan yo'nalishlarni ko'rsatish
SELECT rbr_sequence, city_sequence, getAsRegionName(city_sequence) as city_name_sequence FROM RoutePaths
where to_address_id = 32 ;



create or replace function checkRbrSequenceAndTransport(rbrSequence INT[], transportId INT)

select * from;


select *  from road_transport rt
where rt.road_id = 20 and rt.transport_id = 3;


-- ///////////
insert into public.road_between_region (id, from_address_id, to_address_id, is_directional)
values  (2, 31, 3, true),
        (3, 3, 31, false),
        (4, 29, 31, true),
        (5, 31, 29, false),
        (6, 3, 35, true),
        (7, 35, 3, false),
        (8, 33, 35, true),
        (9, 35, 33, false),
        (10, 33, 37, true),
        (11, 37, 33, false),
        (12, 34, 37, true),
        (13, 37, 34, false),
        (14, 32, 30, true),
        (15, 30, 32, false),
        (16, 29, 30, true),
        (17, 30, 29, false),
        (18, 32, 34, true),
        (19, 34, 32, false),
        (20, 9, 31, true),
        (21, 31, 9, false),
        (22, 9, 38, true),
        (23, 38, 9, false),
        (24, 3, 38, true),
        (25, 38, 3, false);

insert into public.road_transport (id, road_id, transport_id, active, price, is_directional, is_bilateral)
values  (5, 20, 3, true, 27000000.00000, true, true),
        (1, 16, 2, false, 25000000.00000, true, true);

insert into public.transport (id, name, type_id, max_capasity, length, height, width, photo_attachment_id, active, can_be_fully, can_be_partially)
values  (1, 'Jay Trucks', 1, 2500, 6, 2.5, 2, null, false, true, true),
        (2, 'GB Trucks', 1, 5600, 8, 4.5, 3, null, false, true, false),
        (3, 'Turkmani Airlanes', 3, 1500, 2, 2, 2, null, true, false, true);

insert into public.region (id, name_en)
values  (1, 'Yangiaryk'),
        (2, 'Yangibozor'),
        (3, 'Urganch'),
        (4, 'Qarshi'),
        (5, 'Turkman'),
        (6, 'Moskva'),
        (7, 'Quqan'),
        (8, 'Istanbul'),
        (9, 'Ashhabad'),
        (10, 'Termiz'),
        (11, 'Namangan'),
        (12, 'Andijon'),
        (13, 'Astana'),
        (14, 'Tehron'),
        (15, 'Bagdad'),
        (16, 'Islamabad'),
        (17, 'Pekin'),
        (18, 'Ulan Batar'),
        (19, 'Baku'),
        (20, 'Aqtov'),
        (21, 'Bautino'),
        (22, 'Hazar'),
        (23, 'Odessa'),
        (24, 'Batumi'),
        (25, 'Tabriz'),
        (26, 'Isfahon'),
        (27, 'Marv'),
        (28, 'Tokio'),
        (29, 'Yangiyul'),
        (30, 'Jizzakh'),
        (31, 'Tashkent'),
        (32, 'Samarqand'),
        (33, 'Turtkul'),
        (34, 'Bukhara'),
        (35, 'Beruniy'),
        (36, 'Navoiy'),
        (37, 'Gazli'),
        (38, 'Dashoguz');