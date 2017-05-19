--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: media; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE media (
    path character varying,
    id integer
);


ALTER TABLE media OWNER TO postgres;

--
-- Data for Name: media; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY media (path, id) FROM stdin;
files\\dice.png	2
files\\victor.jpg	1
\.


--
-- Name: media_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX media_id_uindex ON media USING btree (id);


--
-- PostgreSQL database dump complete
--

