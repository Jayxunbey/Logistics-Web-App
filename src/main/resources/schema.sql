

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
