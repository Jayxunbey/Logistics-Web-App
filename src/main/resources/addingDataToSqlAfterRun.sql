
-- adding data to who pays
insert into public.who_pays (id, name_en)
values  (default, 'Sender'),
        (default, 'Receiver');


-- adding data to region
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
       ('Baku'),
       ('Aqtov'),
       ('Bautino'),
       ('Hazar'),
       ('Odessa'),
       ('Batumi'),
       ('Tabriz'),
       ('Isfahon'),
       ('Marv'),
       ('Tokio');

-- adding ata to Transport Type

insert into public.transport_type (id, name_en)
values  (default, 'Ship'),
        (default, 'Truck'),
        (default, 'Flight'),
        (default, 'Train');


-- adding data to Cargo Type
insert into public.cargo_type (id, name_en)
values  (default, 'Food'),
        (default, 'Textile Products'),
        (default, 'Medical Products'),
        (default, 'Metal Products'),
        (default, 'Building Materials'),
        (default, 'Furniture'),
        (default, 'Agricultural'),
        (default, 'Equipment and Spare parts'),
        (default, 'Lumber');

-- adding data to Payment Type
insert into public.payment_type (id, name_en, active)
values  (default, 'Cash', true),
        (default, 'Credit Card', true),
        (default, 'Bank Cheque', true);

-- adding data to Order Status
insert into public.order_status (id, name_en)
values  (default, 'Application Received'),
        (default, 'Application Under Review'),
        (default, 'Application Approved'),
        (default, 'Application Rejected'),
        (default, 'Application Updated'),
        (default, 'Preparing the Cargo'),
        (default, 'Ready for Dispatch'),
        (default, 'Cargo Dispatched'),
        (default, 'Cargo In Transit'),
        (default, 'Reached Intermediate Inspection Point'),
        (default, 'Stopped for Maintenance'),
        (default, 'Resuming Transit'),
        (default, 'Truck Arrived at Destination'),
        (default, 'Unloading in Progress'),
        (default, 'Cargo Delivered'),
        (default, 'Delivery Issues'),
        (default, 'Rescheduled Delivery'),
        (default, 'Cargo Being Returned'),
        (default, 'Cargo Returned');

--

-- adding funtions
CREATE OR REPLACE FUNCTION checkIsValidTransportOptions(fromId INT, toId INT, isBilateral boolean, rt road_transport,
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
