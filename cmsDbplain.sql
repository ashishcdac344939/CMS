--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2023-06-20 22:51:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "FRIDR";
--
-- TOC entry 2846 (class 1262 OID 67552)
-- Name: FRIDR; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "FRIDR" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_India.1252' LC_CTYPE = 'English_India.1252';


ALTER DATABASE "FRIDR" OWNER TO postgres;

\connect "FRIDR"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 208 (class 1255 OID 67576)
-- Name: trigger_set_timestamp(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.trigger_set_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	BEGIN
  		NEW.record_tracking = NOW();
  		RETURN NEW;
	END;
	$$;


ALTER FUNCTION public.trigger_set_timestamp() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 207 (class 1259 OID 75749)
-- Name: afcat_qb_mapping; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.afcat_qb_mapping (
    id integer NOT NULL,
    qb_desc character varying,
    set1_qno integer,
    set2_qno integer,
    set3_qno integer,
    set4_qno integer
);


ALTER TABLE public.afcat_qb_mapping OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 75747)
-- Name: afcat_qb_mapping_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.afcat_qb_mapping_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.afcat_qb_mapping_id_seq OWNER TO postgres;

--
-- TOC entry 2847 (class 0 OID 0)
-- Dependencies: 206
-- Name: afcat_qb_mapping_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.afcat_qb_mapping_id_seq OWNED BY public.afcat_qb_mapping.id;


--
-- TOC entry 203 (class 1259 OID 67555)
-- Name: user_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_details (
    id integer NOT NULL,
    mobile_number character varying NOT NULL,
    name character varying,
    email character varying,
    image_url character varying,
    record_tracking time without time zone,
    dob character varying,
    gender character varying
);


ALTER TABLE public.user_details OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 67553)
-- Name: login_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.login_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.login_id_seq OWNER TO postgres;

--
-- TOC entry 2848 (class 0 OID 0)
-- Dependencies: 202
-- Name: login_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.login_id_seq OWNED BY public.user_details.id;


--
-- TOC entry 205 (class 1259 OID 67566)
-- Name: login_temp; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.login_temp (
    id integer NOT NULL,
    mobile_number character varying NOT NULL,
    otp character varying,
    record_tracking time without time zone DEFAULT now()
);


ALTER TABLE public.login_temp OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 67564)
-- Name: login_temp_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.login_temp_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.login_temp_id_seq OWNER TO postgres;

--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 204
-- Name: login_temp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.login_temp_id_seq OWNED BY public.login_temp.id;


--
-- TOC entry 2706 (class 2604 OID 75752)
-- Name: afcat_qb_mapping id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.afcat_qb_mapping ALTER COLUMN id SET DEFAULT nextval('public.afcat_qb_mapping_id_seq'::regclass);


--
-- TOC entry 2704 (class 2604 OID 67569)
-- Name: login_temp id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_temp ALTER COLUMN id SET DEFAULT nextval('public.login_temp_id_seq'::regclass);


--
-- TOC entry 2703 (class 2604 OID 67558)
-- Name: user_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details ALTER COLUMN id SET DEFAULT nextval('public.login_id_seq'::regclass);


--
-- TOC entry 2712 (class 2606 OID 75757)
-- Name: afcat_qb_mapping afcat_qb_mapping_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.afcat_qb_mapping
    ADD CONSTRAINT afcat_qb_mapping_pkey PRIMARY KEY (id);


--
-- TOC entry 2708 (class 2606 OID 67563)
-- Name: user_details login_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details
    ADD CONSTRAINT login_pkey PRIMARY KEY (id, mobile_number);


--
-- TOC entry 2710 (class 2606 OID 67579)
-- Name: login_temp login_temp_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_temp
    ADD CONSTRAINT login_temp_pkey PRIMARY KEY (mobile_number);


--
-- TOC entry 2714 (class 2620 OID 67577)
-- Name: login_temp set_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp BEFORE INSERT OR UPDATE ON public.login_temp FOR EACH ROW EXECUTE FUNCTION public.trigger_set_timestamp();


--
-- TOC entry 2713 (class 2620 OID 67581)
-- Name: user_details set_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp BEFORE INSERT OR UPDATE ON public.user_details FOR EACH ROW EXECUTE FUNCTION public.trigger_set_timestamp();


-- Completed on 2023-06-20 22:51:04

--
-- PostgreSQL database dump complete
--

