PGDMP     3    1                {            FRIDR    12.1    12.1     !           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            "           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            #           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            $           1262    67552    FRIDR    DATABASE     �   CREATE DATABASE "FRIDR" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_India.1252' LC_CTYPE = 'English_India.1252';
    DROP DATABASE "FRIDR";
                postgres    false            �            1255    67576    trigger_set_timestamp()    FUNCTION     �   CREATE FUNCTION public.trigger_set_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	BEGIN
  		NEW.record_tracking = NOW();
  		RETURN NEW;
	END;
	$$;
 .   DROP FUNCTION public.trigger_set_timestamp();
       public          postgres    false            �            1259    75749    afcat_qb_mapping    TABLE     �   CREATE TABLE public.afcat_qb_mapping (
    id integer NOT NULL,
    qb_desc character varying,
    set1_qno integer,
    set2_qno integer,
    set3_qno integer,
    set4_qno integer
);
 $   DROP TABLE public.afcat_qb_mapping;
       public         heap    postgres    false            �            1259    75747    afcat_qb_mapping_id_seq    SEQUENCE     �   CREATE SEQUENCE public.afcat_qb_mapping_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.afcat_qb_mapping_id_seq;
       public          postgres    false    207            %           0    0    afcat_qb_mapping_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.afcat_qb_mapping_id_seq OWNED BY public.afcat_qb_mapping.id;
          public          postgres    false    206            �            1259    67555    user_details    TABLE     +  CREATE TABLE public.user_details (
    id integer NOT NULL,
    mobile_number character varying NOT NULL,
    name character varying,
    email character varying,
    image_url character varying,
    record_tracking time without time zone,
    dob character varying,
    gender character varying
);
     DROP TABLE public.user_details;
       public         heap    postgres    false            �            1259    67553    login_id_seq    SEQUENCE     �   CREATE SEQUENCE public.login_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.login_id_seq;
       public          postgres    false    203            &           0    0    login_id_seq    SEQUENCE OWNED BY     D   ALTER SEQUENCE public.login_id_seq OWNED BY public.user_details.id;
          public          postgres    false    202            �            1259    67566 
   login_temp    TABLE     �   CREATE TABLE public.login_temp (
    id integer NOT NULL,
    mobile_number character varying NOT NULL,
    otp character varying,
    record_tracking time without time zone DEFAULT now()
);
    DROP TABLE public.login_temp;
       public         heap    postgres    false            �            1259    67564    login_temp_id_seq    SEQUENCE     �   CREATE SEQUENCE public.login_temp_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.login_temp_id_seq;
       public          postgres    false    205            '           0    0    login_temp_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.login_temp_id_seq OWNED BY public.login_temp.id;
          public          postgres    false    204            �
           2604    75752    afcat_qb_mapping id    DEFAULT     z   ALTER TABLE ONLY public.afcat_qb_mapping ALTER COLUMN id SET DEFAULT nextval('public.afcat_qb_mapping_id_seq'::regclass);
 B   ALTER TABLE public.afcat_qb_mapping ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    207    207            �
           2604    67569    login_temp id    DEFAULT     n   ALTER TABLE ONLY public.login_temp ALTER COLUMN id SET DEFAULT nextval('public.login_temp_id_seq'::regclass);
 <   ALTER TABLE public.login_temp ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    204    205            �
           2604    67558    user_details id    DEFAULT     k   ALTER TABLE ONLY public.user_details ALTER COLUMN id SET DEFAULT nextval('public.login_id_seq'::regclass);
 >   ALTER TABLE public.user_details ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203                      0    75749    afcat_qb_mapping 
   TABLE DATA           _   COPY public.afcat_qb_mapping (id, qb_desc, set1_qno, set2_qno, set3_qno, set4_qno) FROM stdin;
    public          postgres    false    207   �                 0    67566 
   login_temp 
   TABLE DATA           M   COPY public.login_temp (id, mobile_number, otp, record_tracking) FROM stdin;
    public          postgres    false    205   $                 0    67555    user_details 
   TABLE DATA           o   COPY public.user_details (id, mobile_number, name, email, image_url, record_tracking, dob, gender) FROM stdin;
    public          postgres    false    203   P$       (           0    0    afcat_qb_mapping_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.afcat_qb_mapping_id_seq', 100, true);
          public          postgres    false    206            )           0    0    login_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.login_id_seq', 1, true);
          public          postgres    false    202            *           0    0    login_temp_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.login_temp_id_seq', 8, true);
          public          postgres    false    204            �
           2606    75757 &   afcat_qb_mapping afcat_qb_mapping_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.afcat_qb_mapping
    ADD CONSTRAINT afcat_qb_mapping_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.afcat_qb_mapping DROP CONSTRAINT afcat_qb_mapping_pkey;
       public            postgres    false    207            �
           2606    67563    user_details login_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.user_details
    ADD CONSTRAINT login_pkey PRIMARY KEY (id, mobile_number);
 A   ALTER TABLE ONLY public.user_details DROP CONSTRAINT login_pkey;
       public            postgres    false    203    203            �
           2606    67579    login_temp login_temp_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.login_temp
    ADD CONSTRAINT login_temp_pkey PRIMARY KEY (mobile_number);
 D   ALTER TABLE ONLY public.login_temp DROP CONSTRAINT login_temp_pkey;
       public            postgres    false    205            �
           2620    67577    login_temp set_timestamp    TRIGGER     �   CREATE TRIGGER set_timestamp BEFORE INSERT OR UPDATE ON public.login_temp FOR EACH ROW EXECUTE FUNCTION public.trigger_set_timestamp();
 1   DROP TRIGGER set_timestamp ON public.login_temp;
       public          postgres    false    208    205            �
           2620    67581    user_details set_timestamp    TRIGGER     �   CREATE TRIGGER set_timestamp BEFORE INSERT OR UPDATE ON public.user_details FOR EACH ROW EXECUTE FUNCTION public.trigger_set_timestamp();
 3   DROP TRIGGER set_timestamp ON public.user_details;
       public          postgres    false    203    208               ?  x�]�kn�H����	����`�$�a���?�~�IA0�&�`�Œ���>>n_��d�"[M6��3�-[����r���j��ܽ�����}�ok�J�Q��
6�7���W0��ne�ￂݺE����>����{ڸт�f����6-xn��{��8G���j#yJW��z��ldO��d�z�6��b?_�?l�h����T�L5,�
o�ڕi�E��ۆ�����#�u�I�d����jy�9=�+3�T@�;P�9�//߿��nD��[��y��F��m��?/Hz�`��f������>�>!3�g�\�D1�O�Y=W{yz���8`=�g�܎8�7�$?�S��ۆ�b��qm_��*�.p�X���("P�\�����ՙ1|YӧO��鿢I�H���)��'0i��+"p�U�(VE#�����k"0��;<�%�,(x���t/�kC�����1Z6�i�S�Dg��DL��ׅ��&غ��A�QXJ�[�T^� �11���i_�Z��1�J���.Dx�-�&�x]��Eb��1v���-ُ�総��*�8p���T��r�ATĆ�z��#�*F$��q��)���Gh!��[?�?�GxHܦ�P�z�)y�bDM��=�BB���q`��Xu��+��ƪ��zX9�DV�,\`�Z1֧p�����r{ �RC�}�q Sc���@Kp�2�T������?O[<$7tWF��#���u_�����ݗ�#�G⡷S���h��'2�?�,�����3�-��ґ�M<3�E�/=����	1�%��W��g��Ƭ2���a�2��oGLk�8�9���Y��~f8blLN�G�2I�ʊ��4}�+����6��ǣo�ύ#��7�G�2UcD�(�]��I"M$�W&4L� D#��tQI����x���������qj�Wf���Y:�|�g�#������O��m.�E��*��J,F��v�Y��bd�f��J,5F��5��+�$���h�����������e�H�����Ҁ2R��-��7`}��2�F�X]�r����kl������3�)         1   x��� 0�7�	Q.�Y�����X�8���r]��[�
~���X�         N   x��1�  ����	��4���X.ä��;��ْ�&5S��������z�&�����s0gZ�#I�k@�o��     