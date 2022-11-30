--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2022-11-26 17:30:45

--liquibase formatted sql

--changeset TEGUERA:conges-db-sql-file

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
-- TOC entry 5 (class 2615 OID 25659)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3583 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS '';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 225 (class 1259 OID 25736)
-- Name: acte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acte (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.acte OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25672)
-- Name: agent; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agent (
    id bigint NOT NULL,
    matricule character varying(50) NOT NULL,
    cle_matricule character varying(5),
    password_hash character varying(60) NOT NULL,
    nom character varying(50),
    prenom character varying(150),
    nom_jeune_fille character varying(50),
    sexe character varying(10),
    date_naissance date,
    lieu_naissance character varying(150),
    no_cnib character varying(254),
    date_recrutement date,
    qualite character varying(150),
    date_qualite date,
    email character varying(254),
    actif boolean NOT NULL,
    telephone character varying(20),
    activation_key character varying(20),
    reset_key character varying(20),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    reset_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    deleted boolean DEFAULT false NOT NULL,
    affectation character varying(255),
    categorie character varying(255),
    echelle character varying(255),
    echellon character varying(255),
    grade character varying(255),
    nocnib character varying(255),
    "position" character varying(255),
    corps_id bigint
);


ALTER TABLE public.agent OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 25696)
-- Name: agent_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agent_profile (
    agent_id bigint NOT NULL,
    profile_id bigint NOT NULL
);


ALTER TABLE public.agent_profile OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 25741)
-- Name: agent_structure; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agent_structure (
    id bigint NOT NULL,
    actif boolean NOT NULL,
    agent_id bigint,
    structure_id bigint
);


ALTER TABLE public.agent_structure OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 25746)
-- Name: ampliation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ampliation (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    code character varying(10) NOT NULL,
    libelle character varying(255) NOT NULL
);


ALTER TABLE public.ampliation OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 25751)
-- Name: article; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.article (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    libelle character varying(255),
    code character varying(10) NOT NULL
);


ALTER TABLE public.article OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 25756)
-- Name: avis; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.avis (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    avis_dg character varying(254),
    avis_drh character varying(50) NOT NULL,
    avis_sg character varying(254),
    avis_sh character varying(254),
    demande_id bigint
);


ALTER TABLE public.avis OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 25763)
-- Name: corps; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.corps (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    code character varying(10) NOT NULL,
    libelle character varying(255) NOT NULL
);


ALTER TABLE public.corps OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 25666)
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 25661)
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 25768)
-- Name: demande; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.demande (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    duree_absence bigint,
    lieu_jouissance_bf character varying(254),
    lieu_jouissance_etrang character varying(254),
    numero_demande character varying(254) NOT NULL,
    periode_debut timestamp without time zone,
    periode_fin timestamp without time zone,
    ref_last_decision character varying(254),
    situation_snd character varying(254),
    motif_absence_id bigint,
    type_demande_id bigint,
    utilisateur_id bigint
);


ALTER TABLE public.demande OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 25775)
-- Name: document; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.document (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    reference character varying(254) NOT NULL,
    url character varying(254),
    demande_id bigint
);


ALTER TABLE public.document OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 25782)
-- Name: exemple; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exemple (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    nom character varying(50) NOT NULL
);


ALTER TABLE public.exemple OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 25885)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 25787)
-- Name: ministere; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ministere (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    code character varying(255) NOT NULL,
    description character varying(255),
    libelle character varying(255) NOT NULL,
    sigle character varying(255) NOT NULL
);


ALTER TABLE public.ministere OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 25795)
-- Name: ministere_structure; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ministere_structure (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    date_debut timestamp without time zone,
    date_fin timestamp without time zone,
    statut boolean NOT NULL,
    ministere_id bigint,
    structure_id bigint
);


ALTER TABLE public.ministere_structure OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 25794)
-- Name: ministere_structure_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ministere_structure ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.ministere_structure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 237 (class 1259 OID 25800)
-- Name: motif_absence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motif_absence (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    libelle character varying(254)
);


ALTER TABLE public.motif_absence OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 25805)
-- Name: notification; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notification (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    contenu character varying(2000) NOT NULL,
    objet character varying(250) NOT NULL
);


ALTER TABLE public.notification OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 25812)
-- Name: notification_agent; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notification_agent (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    lu boolean NOT NULL,
    agent_id bigint NOT NULL,
    notification_id bigint NOT NULL
);


ALTER TABLE public.notification_agent OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 25817)
-- Name: payprovider; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payprovider (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    code character varying(255),
    defaultprmid bigint,
    desactiver character(1),
    libelle character varying(255),
    remoteid bigint,
    type_demande bigint
);


ALTER TABLE public.payprovider OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 25712)
-- Name: privilege; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.privilege (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    created_by character varying(50) DEFAULT 'system'::character varying NOT NULL,
    created_date timestamp without time zone DEFAULT now(),
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE public.privilege OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 25711)
-- Name: privilege_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.privilege ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.privilege_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 219 (class 1259 OID 25688)
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    native_profile boolean NOT NULL,
    created_by character varying(50) DEFAULT 'system'::character varying NOT NULL,
    created_date timestamp without time zone DEFAULT now(),
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 25687)
-- Name: profile_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.profile ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 223 (class 1259 OID 25720)
-- Name: profile_privilege; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile_privilege (
    profile_id bigint NOT NULL,
    privilege_id bigint NOT NULL
);


ALTER TABLE public.profile_privilege OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 25671)
-- Name: sequence_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_generator
    START WITH 56
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_generator OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 25735)
-- Name: sequence_generator_1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_generator_1
    START WITH 56
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_generator_1 OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 25824)
-- Name: structure; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.structure (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    active character(1) NOT NULL,
    adresse character varying(255),
    description character varying(255),
    email_resp character varying(255) NOT NULL,
    email_struct character varying(255) NOT NULL,
    libelle character varying(255) NOT NULL,
    responsable character varying(255) NOT NULL,
    sigle character varying(255) NOT NULL,
    telephone character varying(255),
    parent_id bigint,
    type_id bigint
);


ALTER TABLE public.structure OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 25831)
-- Name: type_demande; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_demande (
    id bigint NOT NULL,
    code character varying(255),
    description character varying(255),
    libelle character varying(255) NOT NULL,
    mode_paie boolean,
    solde_annuel bigint
);


ALTER TABLE public.type_demande OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 25838)
-- Name: type_structure; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_structure (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    description character varying(255),
    libelle character varying(255) NOT NULL
);


ALTER TABLE public.type_structure OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 25845)
-- Name: type_visa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_visa (
    type_demande_id bigint NOT NULL,
    visa_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    numero_ordre bigint NOT NULL
);


ALTER TABLE public.type_visa OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 25850)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    actif boolean,
    email character varying(254),
    matricule character varying(254),
    mot_de_passe character varying(254),
    nom character varying(254),
    prenom character varying(254),
    telephone character varying(254),
    structure_id bigint
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 25857)
-- Name: utilisateur_profiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur_profiles (
    utilisateur_id bigint NOT NULL,
    profiles_id bigint NOT NULL
);


ALTER TABLE public.utilisateur_profiles OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 25862)
-- Name: visa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.visa (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    deleted boolean NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    code character varying(50) NOT NULL,
    libelle character varying(254)
);


ALTER TABLE public.visa OWNER TO postgres;

--
-- TOC entry 3554 (class 0 OID 25736)
-- Dependencies: 225
-- Data for Name: acte; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.acte (id, created_by, created_date, deleted, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- TOC entry 3546 (class 0 OID 25672)
-- Dependencies: 217
-- Data for Name: agent; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agent (id, matricule, cle_matricule, password_hash, nom, prenom, nom_jeune_fille, sexe, date_naissance, lieu_naissance, no_cnib, date_recrutement, qualite, date_qualite, email, actif, telephone, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date, deleted, affectation, categorie, echelle, echellon, grade, nocnib, "position", corps_id) FROM stdin;
1	system	S	$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG	System	System	\N	0	\N	\N	\N	\N	\N	\N	system@localhost.bf	t	\N	\N	\N	system	2022-11-26 16:56:43.683924	\N	system	\N	f	\N	\N	\N	\N	\N	\N	\N	\N
2	admin	S	$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC	Administrator	Administrator	\N	0	\N	\N	\N	\N	\N	\N	admin@localhost.bf	t	\N	\N	\N	system	2022-11-26 16:56:43.683924	\N	system	\N	f	\N	\N	\N	\N	\N	\N	\N	\N
3	user	S	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	User	User	\N	0	\N	\N	\N	\N	\N	\N	user@localhost.bf	t	\N	\N	\N	system	2022-11-26 16:56:43.683924	\N	system	\N	f	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 3549 (class 0 OID 25696)
-- Dependencies: 220
-- Data for Name: agent_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agent_profile (agent_id, profile_id) FROM stdin;
1	1
2	1
3	2
\.


--
-- TOC entry 3555 (class 0 OID 25741)
-- Dependencies: 226
-- Data for Name: agent_structure; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agent_structure (id, actif, agent_id, structure_id) FROM stdin;
4	t	2	3
5	t	3	3
\.


--
-- TOC entry 3556 (class 0 OID 25746)
-- Dependencies: 227
-- Data for Name: ampliation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ampliation (id, created_by, created_date, deleted, last_modified_by, last_modified_date, code, libelle) FROM stdin;
\.


--
-- TOC entry 3557 (class 0 OID 25751)
-- Dependencies: 228
-- Data for Name: article; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.article (id, created_by, created_date, deleted, last_modified_by, last_modified_date, libelle, code) FROM stdin;
\.


--
-- TOC entry 3558 (class 0 OID 25756)
-- Dependencies: 229
-- Data for Name: avis; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.avis (id, created_by, created_date, deleted, last_modified_by, last_modified_date, avis_dg, avis_drh, avis_sg, avis_sh, demande_id) FROM stdin;
\.


--
-- TOC entry 3559 (class 0 OID 25763)
-- Dependencies: 230
-- Data for Name: corps; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.corps (id, created_by, created_date, deleted, last_modified_by, last_modified_date, code, libelle) FROM stdin;
\.


--
-- TOC entry 3544 (class 0 OID 25666)
-- Dependencies: 215
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
management_generator	STGVAP-DEV-TEAM	config/liquibase/changelog/user_management_schema.xml	2022-11-26 16:56:43.680123	1	EXECUTED	8:fe7f1e2c59fd19eac860deab387e67d4	createSequence sequenceName=sequence_generator		\N	4.6.1	\N	\N	9481803590
00000000000001	STGVAP-DEV-TEAM	config/liquibase/changelog/user_management_schema.xml	2022-11-26 16:56:43.762442	2	EXECUTED	8:bfac9d0d53c7264bbcb34c76994ae733	createTable tableName=agent; createTable tableName=profile; createTable tableName=agent_profile; addPrimaryKey tableName=agent_profile; addForeignKeyConstraint baseTableName=agent_profile, constraintName=fk_profile_id, referencedTableName=profile;...		\N	4.6.1	\N	\N	9481803590
management_generator_1	STGVAP-DEV-TEAM	config/liquibase/changelog/privilege_1.xml	2022-11-26 16:56:43.766373	3	EXECUTED	8:938fcac825f7814534d114ac3ca8555d	createSequence sequenceName=sequence_generator_1		\N	4.6.1	\N	\N	9481803590
00000000000002	STGVAP-DEV-TEAM	config/liquibase/changelog/privilege_1.xml	2022-11-26 16:56:43.769988	4	EXECUTED	8:ed9de78edcada609f5bcd82c7b6f1277	loadData tableName=privilege		\N	4.6.1	\N	\N	9481803590
\.


--
-- TOC entry 3543 (class 0 OID 25661)
-- Dependencies: 214
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- TOC entry 3560 (class 0 OID 25768)
-- Dependencies: 231
-- Data for Name: demande; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.demande (id, created_by, created_date, deleted, last_modified_by, last_modified_date, duree_absence, lieu_jouissance_bf, lieu_jouissance_etrang, numero_demande, periode_debut, periode_fin, ref_last_decision, situation_snd, motif_absence_id, type_demande_id, utilisateur_id) FROM stdin;
\.


--
-- TOC entry 3561 (class 0 OID 25775)
-- Dependencies: 232
-- Data for Name: document; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.document (id, created_by, created_date, deleted, last_modified_by, last_modified_date, reference, url, demande_id) FROM stdin;
\.


--
-- TOC entry 3562 (class 0 OID 25782)
-- Dependencies: 233
-- Data for Name: exemple; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.exemple (id, created_by, created_date, deleted, last_modified_by, last_modified_date, nom) FROM stdin;
\.


--
-- TOC entry 3563 (class 0 OID 25787)
-- Dependencies: 234
-- Data for Name: ministere; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ministere (id, created_by, created_date, deleted, last_modified_by, last_modified_date, code, description, libelle, sigle) FROM stdin;
1	system	2022-11-26 16:56:45.621771	f	system	2022-11-26 16:56:45.621771	00-TEST	\N	Ministere en charge de la fonction publique - TEST	MFP - TEST
\.


--
-- TOC entry 3565 (class 0 OID 25795)
-- Dependencies: 236
-- Data for Name: ministere_structure; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ministere_structure (id, created_by, created_date, deleted, last_modified_by, last_modified_date, date_debut, date_fin, statut, ministere_id, structure_id) FROM stdin;
1	system	2022-11-26 16:56:45.669245	f	system	2022-11-26 16:56:45.669245	2022-11-26 16:56:45.666	\N	t	1	3
\.


--
-- TOC entry 3566 (class 0 OID 25800)
-- Dependencies: 237
-- Data for Name: motif_absence; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.motif_absence (id, created_by, created_date, deleted, last_modified_by, last_modified_date, libelle) FROM stdin;
\.


--
-- TOC entry 3567 (class 0 OID 25805)
-- Dependencies: 238
-- Data for Name: notification; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notification (id, created_by, created_date, deleted, last_modified_by, last_modified_date, contenu, objet) FROM stdin;
\.


--
-- TOC entry 3568 (class 0 OID 25812)
-- Dependencies: 239
-- Data for Name: notification_agent; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notification_agent (id, created_by, created_date, deleted, last_modified_by, last_modified_date, lu, agent_id, notification_id) FROM stdin;
\.


--
-- TOC entry 3569 (class 0 OID 25817)
-- Dependencies: 240
-- Data for Name: payprovider; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payprovider (id, created_by, created_date, deleted, last_modified_by, last_modified_date, code, defaultprmid, desactiver, libelle, remoteid, type_demande) FROM stdin;
\.


--
-- TOC entry 3551 (class 0 OID 25712)
-- Dependencies: 222
-- Data for Name: privilege; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.privilege (id, name, created_by, created_date, last_modified_by, last_modified_date, deleted) FROM stdin;
1	ROLE_ADMIN	system	2022-11-26 16:56:43.683924	\N	\N	f
2	ROLE_USER	system	2022-11-26 16:56:43.683924	\N	\N	f
3	ROLE_GESTIONNAIRE	system	2022-11-26 16:56:43.768248	\N	\N	f
4	ROLE_INTERNAUTE	system	2022-11-26 16:56:43.768248	\N	\N	f
\.


--
-- TOC entry 3548 (class 0 OID 25688)
-- Dependencies: 219
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (id, name, native_profile, created_by, created_date, last_modified_by, last_modified_date, deleted) FROM stdin;
1	ADMIN	t	system	2022-11-26 16:56:43.683924	\N	\N	f
2	USER	f	system	2022-11-26 16:56:43.683924	\N	\N	f
\.


--
-- TOC entry 3552 (class 0 OID 25720)
-- Dependencies: 223
-- Data for Name: profile_privilege; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile_privilege (profile_id, privilege_id) FROM stdin;
1	1
2	2
\.


--
-- TOC entry 3570 (class 0 OID 25824)
-- Dependencies: 241
-- Data for Name: structure; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.structure (id, created_by, created_date, deleted, last_modified_by, last_modified_date, active, adresse, description, email_resp, email_struct, libelle, responsable, sigle, telephone, parent_id, type_id) FROM stdin;
3	system	2022-11-26 16:56:45.664243	f	system	2022-11-26 16:56:45.664243	Y	\N	\N	contact.test@fp.gov.bf	contact.test@fp.gov.bf	Structure de Test	contact.test@fp.gov.bf	STRUC - TEST	00.00.00.00	\N	2
\.


--
-- TOC entry 3571 (class 0 OID 25831)
-- Dependencies: 242
-- Data for Name: type_demande; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_demande (id, code, description, libelle, mode_paie, solde_annuel) FROM stdin;
\.


--
-- TOC entry 3572 (class 0 OID 25838)
-- Dependencies: 243
-- Data for Name: type_structure; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_structure (id, created_by, created_date, deleted, last_modified_by, last_modified_date, description, libelle) FROM stdin;
2	system	2022-11-26 16:56:45.659313	f	system	2022-11-26 16:56:45.659313	\N	CENTRALE
\.


--
-- TOC entry 3573 (class 0 OID 25845)
-- Dependencies: 244
-- Data for Name: type_visa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_visa (type_demande_id, visa_id, created_by, created_date, deleted, last_modified_by, last_modified_date, numero_ordre) FROM stdin;
\.


--
-- TOC entry 3574 (class 0 OID 25850)
-- Dependencies: 245
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utilisateur (id, created_by, created_date, deleted, last_modified_by, last_modified_date, actif, email, matricule, mot_de_passe, nom, prenom, telephone, structure_id) FROM stdin;
\.


--
-- TOC entry 3575 (class 0 OID 25857)
-- Dependencies: 246
-- Data for Name: utilisateur_profiles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utilisateur_profiles (utilisateur_id, profiles_id) FROM stdin;
\.


--
-- TOC entry 3576 (class 0 OID 25862)
-- Dependencies: 247
-- Data for Name: visa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.visa (id, created_by, created_date, deleted, last_modified_by, last_modified_date, code, libelle) FROM stdin;
\.


--
-- TOC entry 3585 (class 0 OID 0)
-- Dependencies: 248
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 5, true);


--
-- TOC entry 3586 (class 0 OID 0)
-- Dependencies: 235
-- Name: ministere_structure_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ministere_structure_id_seq', 1, true);


--
-- TOC entry 3587 (class 0 OID 0)
-- Dependencies: 221
-- Name: privilege_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.privilege_id_seq', 1, false);


--
-- TOC entry 3588 (class 0 OID 0)
-- Dependencies: 218
-- Name: profile_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_id_seq', 1, false);


--
-- TOC entry 3589 (class 0 OID 0)
-- Dependencies: 216
-- Name: sequence_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_generator', 56, false);


--
-- TOC entry 3590 (class 0 OID 0)
-- Dependencies: 224
-- Name: sequence_generator_1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_generator_1', 56, false);


--
-- TOC entry 3318 (class 2606 OID 25740)
-- Name: acte acte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acte
    ADD CONSTRAINT acte_pkey PRIMARY KEY (id);


--
-- TOC entry 3300 (class 2606 OID 25680)
-- Name: agent agent_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT agent_pkey PRIMARY KEY (id);


--
-- TOC entry 3312 (class 2606 OID 25700)
-- Name: agent_profile agent_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent_profile
    ADD CONSTRAINT agent_profile_pkey PRIMARY KEY (agent_id, profile_id);


--
-- TOC entry 3320 (class 2606 OID 25745)
-- Name: agent_structure agent_structure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent_structure
    ADD CONSTRAINT agent_structure_pkey PRIMARY KEY (id);


--
-- TOC entry 3322 (class 2606 OID 25750)
-- Name: ampliation ampliation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ampliation
    ADD CONSTRAINT ampliation_pkey PRIMARY KEY (id);


--
-- TOC entry 3324 (class 2606 OID 25755)
-- Name: article article_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);


--
-- TOC entry 3326 (class 2606 OID 25762)
-- Name: avis avis_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avis
    ADD CONSTRAINT avis_pkey PRIMARY KEY (id);


--
-- TOC entry 3328 (class 2606 OID 25767)
-- Name: corps corps_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.corps
    ADD CONSTRAINT corps_pkey PRIMARY KEY (id);


--
-- TOC entry 3298 (class 2606 OID 25665)
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- TOC entry 3330 (class 2606 OID 25774)
-- Name: demande demande_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.demande
    ADD CONSTRAINT demande_pkey PRIMARY KEY (id);


--
-- TOC entry 3334 (class 2606 OID 25781)
-- Name: document document_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (id);


--
-- TOC entry 3338 (class 2606 OID 25786)
-- Name: exemple exemple_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exemple
    ADD CONSTRAINT exemple_pkey PRIMARY KEY (id);


--
-- TOC entry 3340 (class 2606 OID 25793)
-- Name: ministere ministere_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ministere
    ADD CONSTRAINT ministere_pkey PRIMARY KEY (id);


--
-- TOC entry 3344 (class 2606 OID 25799)
-- Name: ministere_structure ministere_structure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ministere_structure
    ADD CONSTRAINT ministere_structure_pkey PRIMARY KEY (id);


--
-- TOC entry 3346 (class 2606 OID 25804)
-- Name: motif_absence motif_absence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motif_absence
    ADD CONSTRAINT motif_absence_pkey PRIMARY KEY (id);


--
-- TOC entry 3350 (class 2606 OID 25816)
-- Name: notification_agent notification_agent_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_agent
    ADD CONSTRAINT notification_agent_pkey PRIMARY KEY (id);


--
-- TOC entry 3348 (class 2606 OID 25811)
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


--
-- TOC entry 3352 (class 2606 OID 25823)
-- Name: payprovider payprovider_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payprovider
    ADD CONSTRAINT payprovider_pkey PRIMARY KEY (id);


--
-- TOC entry 3314 (class 2606 OID 25719)
-- Name: privilege privilege_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.privilege
    ADD CONSTRAINT privilege_pkey PRIMARY KEY (id);


--
-- TOC entry 3310 (class 2606 OID 25695)
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);


--
-- TOC entry 3316 (class 2606 OID 25724)
-- Name: profile_privilege profile_privilege_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_privilege
    ADD CONSTRAINT profile_privilege_pkey PRIMARY KEY (profile_id, privilege_id);


--
-- TOC entry 3354 (class 2606 OID 25830)
-- Name: structure structure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.structure
    ADD CONSTRAINT structure_pkey PRIMARY KEY (id);


--
-- TOC entry 3358 (class 2606 OID 25837)
-- Name: type_demande type_demande_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_demande
    ADD CONSTRAINT type_demande_pkey PRIMARY KEY (id);


--
-- TOC entry 3364 (class 2606 OID 25844)
-- Name: type_structure type_structure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_structure
    ADD CONSTRAINT type_structure_pkey PRIMARY KEY (id);


--
-- TOC entry 3368 (class 2606 OID 25849)
-- Name: type_visa type_visa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_visa
    ADD CONSTRAINT type_visa_pkey PRIMARY KEY (type_demande_id, visa_id);


--
-- TOC entry 3356 (class 2606 OID 25876)
-- Name: structure uk_2w71n1qwuytw1l0h3wqakevnr; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.structure
    ADD CONSTRAINT uk_2w71n1qwuytw1l0h3wqakevnr UNIQUE (libelle);


--
-- TOC entry 3366 (class 2606 OID 25882)
-- Name: type_structure uk_igf6rvaxfjf8rf6byej2a1297; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_structure
    ADD CONSTRAINT uk_igf6rvaxfjf8rf6byej2a1297 UNIQUE (libelle);


--
-- TOC entry 3302 (class 2606 OID 25868)
-- Name: agent uk_iwenbmfceiuye98k5dreb039n; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT uk_iwenbmfceiuye98k5dreb039n UNIQUE (nocnib);


--
-- TOC entry 3360 (class 2606 OID 25880)
-- Name: type_demande uk_jam6wing0sh0f19ma8oeitiui; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_demande
    ADD CONSTRAINT uk_jam6wing0sh0f19ma8oeitiui UNIQUE (mode_paie);


--
-- TOC entry 3332 (class 2606 OID 25870)
-- Name: demande uk_mixqdu5vmnokt1cmym6v29ph1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.demande
    ADD CONSTRAINT uk_mixqdu5vmnokt1cmym6v29ph1 UNIQUE (numero_demande);


--
-- TOC entry 3362 (class 2606 OID 25878)
-- Name: type_demande uk_nl0sktq1i984r0mx7cpulb9h9; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_demande
    ADD CONSTRAINT uk_nl0sktq1i984r0mx7cpulb9h9 UNIQUE (libelle);


--
-- TOC entry 3372 (class 2606 OID 25884)
-- Name: utilisateur_profiles uk_oevs9gqiek7rfgy3x33gqykgn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur_profiles
    ADD CONSTRAINT uk_oevs9gqiek7rfgy3x33gqykgn UNIQUE (profiles_id);


--
-- TOC entry 3336 (class 2606 OID 25872)
-- Name: document uk_q3w4qhtph6vt293vlftbaxb4w; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT uk_q3w4qhtph6vt293vlftbaxb4w UNIQUE (reference);


--
-- TOC entry 3342 (class 2606 OID 25874)
-- Name: ministere uk_tixm10ja76giu87k0ovs3qij4; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ministere
    ADD CONSTRAINT uk_tixm10ja76giu87k0ovs3qij4 UNIQUE (code);


--
-- TOC entry 3370 (class 2606 OID 25856)
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- TOC entry 3374 (class 2606 OID 25861)
-- Name: utilisateur_profiles utilisateur_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur_profiles
    ADD CONSTRAINT utilisateur_profiles_pkey PRIMARY KEY (utilisateur_id, profiles_id);


--
-- TOC entry 3304 (class 2606 OID 25684)
-- Name: agent ux_agent_cnib; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT ux_agent_cnib UNIQUE (no_cnib);


--
-- TOC entry 3306 (class 2606 OID 25686)
-- Name: agent ux_agent_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT ux_agent_email UNIQUE (email);


--
-- TOC entry 3308 (class 2606 OID 25682)
-- Name: agent ux_agent_matricule; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT ux_agent_matricule UNIQUE (matricule);


--
-- TOC entry 3376 (class 2606 OID 25866)
-- Name: visa visa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.visa
    ADD CONSTRAINT visa_pkey PRIMARY KEY (id);


--
-- TOC entry 3399 (class 2606 OID 25981)
-- Name: utilisateur_profiles fk14c5q87vqk91ayipb1qqg1idf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur_profiles
    ADD CONSTRAINT fk14c5q87vqk91ayipb1qqg1idf FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- TOC entry 3384 (class 2606 OID 25901)
-- Name: avis fk6cw43hgkpddjdlr7kc37no0hc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avis
    ADD CONSTRAINT fk6cw43hgkpddjdlr7kc37no0hc FOREIGN KEY (demande_id) REFERENCES public.demande(id);


--
-- TOC entry 3394 (class 2606 OID 25956)
-- Name: structure fk7t1svju3eor611k0a9te3mfv9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.structure
    ADD CONSTRAINT fk7t1svju3eor611k0a9te3mfv9 FOREIGN KEY (type_id) REFERENCES public.type_structure(id);


--
-- TOC entry 3398 (class 2606 OID 25971)
-- Name: utilisateur fk94ugam29b228vv31j58pli2rs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT fk94ugam29b228vv31j58pli2rs FOREIGN KEY (structure_id) REFERENCES public.structure(id);


--
-- TOC entry 3378 (class 2606 OID 25706)
-- Name: agent_profile fk_agent_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent_profile
    ADD CONSTRAINT fk_agent_id FOREIGN KEY (agent_id) REFERENCES public.agent(id);


--
-- TOC entry 3380 (class 2606 OID 25730)
-- Name: profile_privilege fk_privilege_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_privilege
    ADD CONSTRAINT fk_privilege_id FOREIGN KEY (privilege_id) REFERENCES public.privilege(id);


--
-- TOC entry 3379 (class 2606 OID 25701)
-- Name: agent_profile fk_profile_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent_profile
    ADD CONSTRAINT fk_profile_id FOREIGN KEY (profile_id) REFERENCES public.profile(id);


--
-- TOC entry 3381 (class 2606 OID 25725)
-- Name: profile_privilege fk_profile_id_privilege; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_privilege
    ADD CONSTRAINT fk_profile_id_privilege FOREIGN KEY (profile_id) REFERENCES public.profile(id);


--
-- TOC entry 3388 (class 2606 OID 25921)
-- Name: document fkb7kqrvthdcjlttujendy1sm88; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkb7kqrvthdcjlttujendy1sm88 FOREIGN KEY (demande_id) REFERENCES public.demande(id);


--
-- TOC entry 3396 (class 2606 OID 25961)
-- Name: type_visa fkcwlbwmd248py6bvo5b7m9xfrm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_visa
    ADD CONSTRAINT fkcwlbwmd248py6bvo5b7m9xfrm FOREIGN KEY (type_demande_id) REFERENCES public.type_demande(id);


--
-- TOC entry 3382 (class 2606 OID 25896)
-- Name: agent_structure fkef3mpc788aob23c8cliqyuvu7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent_structure
    ADD CONSTRAINT fkef3mpc788aob23c8cliqyuvu7 FOREIGN KEY (structure_id) REFERENCES public.structure(id);


--
-- TOC entry 3395 (class 2606 OID 25951)
-- Name: structure fkfl4hab0br353bcb4ia3ikqtq3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.structure
    ADD CONSTRAINT fkfl4hab0br353bcb4ia3ikqtq3 FOREIGN KEY (parent_id) REFERENCES public.structure(id);


--
-- TOC entry 3389 (class 2606 OID 25931)
-- Name: ministere_structure fkgks0jjjhi5fuyi6iva5ceb5yd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ministere_structure
    ADD CONSTRAINT fkgks0jjjhi5fuyi6iva5ceb5yd FOREIGN KEY (structure_id) REFERENCES public.structure(id);


--
-- TOC entry 3385 (class 2606 OID 25916)
-- Name: demande fkhdkthdysq1tt65idh25fimwa0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.demande
    ADD CONSTRAINT fkhdkthdysq1tt65idh25fimwa0 FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- TOC entry 3393 (class 2606 OID 25946)
-- Name: payprovider fklfne2qq12sq5r0t1dlxqq34eh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payprovider
    ADD CONSTRAINT fklfne2qq12sq5r0t1dlxqq34eh FOREIGN KEY (type_demande) REFERENCES public.type_demande(id);


--
-- TOC entry 3390 (class 2606 OID 25926)
-- Name: ministere_structure fkljhws7uaixah1p936ot5agsvv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ministere_structure
    ADD CONSTRAINT fkljhws7uaixah1p936ot5agsvv FOREIGN KEY (ministere_id) REFERENCES public.ministere(id);


--
-- TOC entry 3383 (class 2606 OID 25891)
-- Name: agent_structure fklx5e28ylvq1fo8v85ucxrubte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent_structure
    ADD CONSTRAINT fklx5e28ylvq1fo8v85ucxrubte FOREIGN KEY (agent_id) REFERENCES public.agent(id);


--
-- TOC entry 3377 (class 2606 OID 25886)
-- Name: agent fko5p2oy56ylalwguml7s18ur3y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT fko5p2oy56ylalwguml7s18ur3y FOREIGN KEY (corps_id) REFERENCES public.corps(id);


--
-- TOC entry 3391 (class 2606 OID 25941)
-- Name: notification_agent fkoeowv0unw00h7m12riftru5im; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_agent
    ADD CONSTRAINT fkoeowv0unw00h7m12riftru5im FOREIGN KEY (notification_id) REFERENCES public.notification(id);


--
-- TOC entry 3392 (class 2606 OID 25936)
-- Name: notification_agent fkpasg675o4rwnsa6rvaueac9y1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_agent
    ADD CONSTRAINT fkpasg675o4rwnsa6rvaueac9y1 FOREIGN KEY (agent_id) REFERENCES public.agent(id);


--
-- TOC entry 3400 (class 2606 OID 25976)
-- Name: utilisateur_profiles fkpokkkesknxr2g1qcn9nmv9ybq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur_profiles
    ADD CONSTRAINT fkpokkkesknxr2g1qcn9nmv9ybq FOREIGN KEY (profiles_id) REFERENCES public.profile(id);


--
-- TOC entry 3386 (class 2606 OID 25911)
-- Name: demande fkqednfe93bca6g35sse9r4x3o1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.demande
    ADD CONSTRAINT fkqednfe93bca6g35sse9r4x3o1 FOREIGN KEY (type_demande_id) REFERENCES public.type_demande(id);


--
-- TOC entry 3387 (class 2606 OID 25906)
-- Name: demande fkrfb3upedruqoe2ny7tfutlr1m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.demande
    ADD CONSTRAINT fkrfb3upedruqoe2ny7tfutlr1m FOREIGN KEY (motif_absence_id) REFERENCES public.motif_absence(id);


--
-- TOC entry 3397 (class 2606 OID 25966)
-- Name: type_visa fkry4drqfjf7qysdcy3gp75waiy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_visa
    ADD CONSTRAINT fkry4drqfjf7qysdcy3gp75waiy FOREIGN KEY (visa_id) REFERENCES public.visa(id);


--
-- TOC entry 3584 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2022-11-26 17:30:46

--
-- PostgreSQL database dump complete
--

