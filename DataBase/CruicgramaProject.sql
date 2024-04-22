PGDMP                          |            CrucigramProject    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17104    CrucigramProject    DATABASE     �   CREATE DATABASE "CrucigramProject" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Colombia.1252';
 "   DROP DATABASE "CrucigramProject";
                postgres    false            �            1259    17210    crucigramas    TABLE     V   CREATE TABLE public.crucigramas (
    indice integer NOT NULL,
    crucigrama text
);
    DROP TABLE public.crucigramas;
       public         heap    postgres    false            �            1259    17209    crucigramas_indice_seq    SEQUENCE     �   CREATE SEQUENCE public.crucigramas_indice_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.crucigramas_indice_seq;
       public          postgres    false    217                       0    0    crucigramas_indice_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.crucigramas_indice_seq OWNED BY public.crucigramas.indice;
          public          postgres    false    216            �            1259    17164    diccionario    TABLE     �   CREATE TABLE public.diccionario (
    indice integer NOT NULL,
    respuesta character varying(255),
    enunciado character varying(255)
);
    DROP TABLE public.diccionario;
       public         heap    postgres    false            �            1259    17163    diccionario_indice_seq    SEQUENCE     �   CREATE SEQUENCE public.diccionario_indice_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.diccionario_indice_seq;
       public          postgres    false    215            	           0    0    diccionario_indice_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.diccionario_indice_seq OWNED BY public.diccionario.indice;
          public          postgres    false    214            k           2604    17213    crucigramas indice    DEFAULT     x   ALTER TABLE ONLY public.crucigramas ALTER COLUMN indice SET DEFAULT nextval('public.crucigramas_indice_seq'::regclass);
 A   ALTER TABLE public.crucigramas ALTER COLUMN indice DROP DEFAULT;
       public          postgres    false    216    217    217            j           2604    17167    diccionario indice    DEFAULT     x   ALTER TABLE ONLY public.diccionario ALTER COLUMN indice SET DEFAULT nextval('public.diccionario_indice_seq'::regclass);
 A   ALTER TABLE public.diccionario ALTER COLUMN indice DROP DEFAULT;
       public          postgres    false    214    215    215                      0    17210    crucigramas 
   TABLE DATA           9   COPY public.crucigramas (indice, crucigrama) FROM stdin;
    public          postgres    false    217   �       �          0    17164    diccionario 
   TABLE DATA           C   COPY public.diccionario (indice, respuesta, enunciado) FROM stdin;
    public          postgres    false    215   �       
           0    0    crucigramas_indice_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.crucigramas_indice_seq', 1, false);
          public          postgres    false    216                       0    0    diccionario_indice_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.diccionario_indice_seq', 1, false);
          public          postgres    false    214            o           2606    17217    crucigramas crucigramas_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.crucigramas
    ADD CONSTRAINT crucigramas_pkey PRIMARY KEY (indice);
 F   ALTER TABLE ONLY public.crucigramas DROP CONSTRAINT crucigramas_pkey;
       public            postgres    false    217            m           2606    17171    diccionario diccionario_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.diccionario
    ADD CONSTRAINT diccionario_pkey PRIMARY KEY (indice);
 F   ALTER TABLE ONLY public.diccionario DROP CONSTRAINT diccionario_pkey;
       public            postgres    false    215                 x�eSَ�0|�>����Aɴ��C��ݠ��ҁS�2b�����e"[?ϩ3��ӷ�����)R�w�����!�rc��$�uJ���^�ܧ��Xo[��{Y�6�ԥI�3�( 
�!�:�>[�e;;��ʉ�ϕ��>O�ZS]c�ď�@���_l����i�T�kѻN�i9�8[��6�r�YK�/ӳ����c����83�BKl���P��zb-���D�,�u�>�ȇ&1@�&B!):JQ� �=� r��@������M�@y%���c�F�O9��`X4�n5��ZDyӈ=��:�S+�"t�}G��M��4��}���m2�M�&w�$�^�q}U�5�u�o�cљ��Mu��1q�{�"�Z!��v�����{ˊ�)���|;}�F�Ġ��(��4��'-Pg�ū�S�hJ^�`+ۭP1�NŤ���A�(��µx*�E����|�:d�/0�������U�Ѥ<�q�6)����P�f�3n�^MIݟι�_�      �      x��}�r�ȶ��
�#�|?�˰)B��S���$D�t ����G�5�qOzX�����˿��N� %�n�I�K��Hd�����X7�M����ոwA�F�𧫖e�8l�M�p�K�4�i��W6��t]���6
㟮ږ=�f���e����Vicƍ`���(�骃1�;w������h�66Y���U��]���F��.�ל���m�A~�x̊]�7R�G��U�_u��bj[��wΉ�FG��Q�8z�rOA��F�F��ce�U�T�����YY�)�hp<�am��o;��P��<��a��US��Llk�ן�$Z��%�"�L.�{���r�m��Dc�܃�ÂM\,���-$��8�?]1ƞ�֧ ��u�毫 ^��4��N� Yc���>駫f-�{�w��S�a�i5��q��e�ZM�s�v�>ld�S�"O�έ<F@����G96o��[@��;�}���eC6A��F`:NzW<�K�����1̂� ������և"�Ț �,؅�?;ˎ�-�T�T�Ɋ'�%�e`F�[�����g����U�G�^��u� W- `r����޳�i��O g^�y���E4v�)��?Vp�s������?��6ZW����ll�u����f8������6'�H ��7\���\�*X�0�G�oqV����k�r����3��t�8�o`'zo���9�� {��`�qH��q��7�o��9��#�+���%��>�^������1��S���}U(�B�a&+�T6T��)Ͱ��lS��/X)�w��y�s��xrl^7xń]���	�<ML�iO���mG� n0T�#�<©X����$(Q�f� ��-��{p����Eې`HqS�<�<���R ���+=�,�S�����#}x��G�K���4S.b6`�����죘W���[v���ո��J�,P���!��{�֝L<�>��c��E�ef��2��a�r?yk��΂�UP�{�� o��
���v����Վg���]u �g {|���<�1W_�>$�80�}�5S^g��+���<P��
�M2�o�`����oO�i��+\<A �@@Q4���NB�f��V@����S�i���+�p�t��;{��-��ط�`�k"/���/�6HR>&
A6&�-B�����{&N2�t	���?!!(��������JV���ܪp'�2E"o�gd�K�7��A �|�ྒྷ��k�ӂb��� o�@~���jq����,���bV�ڀL"����W¶~��+�m��{�;9e`?x�FV�'�������[��.ͮ�@�'�>�"�6�kv�hd/�9�����{�]@�3�/|�p*AR���ہ��|8^u�����}�#7>EZR�{=�#9�n�$p΋�"?�J}R��-�$���ru� ���E����H>��b��3�M����,�iA,��=�S:���`�J# H@�Td�c��2����k�No��+�����`�zO6���D|�o��{��Zr���HO%D���g@*��.���D� (L�g
���`��2����&0iۺ��:Vѵ��3]��4�.��Z7�t/	�a���;�c�oC��5l#�ճl^���N����ܟ^pG���\��B-�ʲh�-����@ų�0�8�ǎ|�u$�+p큪D(��Wz�Iƛ��3�(N<�U��`�Oa���8p�o�T��l��xnp~ �����{��SI�7Aȷa�5%H
r���(�_�����[���c�tg&P7��'�9��8B����T�(����)�+��*���"���V�뀵N2��-��(�k�Y�8�r��/�\���W��Hn9�	1���=���kʹ��� �X7Թk:,�w`<Iw�Ӹ�t97�}����TV	(U#�k��T��{�����G�ӕ,y��2��9��ƛ�0"�]�$���GD��+�ܱ;����pp�؛��	�~:h�Թ��t掌�GS_YrXQ����� `�7��~ WU��E�z�)���apz���9�مy��+��F�/�(~���?]:�.���������r������u���Z�@�;{��g����`���;�K�e f/e��3���@6ʚ�i�?¥��vz4roSX���Q`�4 �&g�y���<\%x� �S�(/-�u@��Ƈo[Ѱ0h�fP%!�ɠ˱��,Lx�K�����[�� � >onO���1]O���n�a��Q3�!������l}en����� ���e�p����e��0���Qn���z~G�p�q���b���	X���2���|�j��]�#������D�`�F����!/�{5�<Ii�$� ���f�:M��^�c�ù�j�������
�"��]H3U��~���6Xf��H�4!Cs`���X��qc#�`5�}1�xz`y?b��FQ<���2�G^��/����Q�[A匿Q�T���=ng���m|�¸1�<H:�ST��+hsf?�a^3"�=k@Qʕ��`EW�6�#������8���������EYy�z�V��_6p_v-�Q�&t�@ӈ�x�u�a�$k�4p�v�ozwcl} �.	�늒�#��&V�N-��B¤&ᙧ�N�8x��f�ԇo0�C�Im(�s�szlՖ㥟v"��v){,w��s���S�=5�`_��I�rV`:�'�jj��W�X���W�D�f]��b�$Ҧw�K���	��G�'��9�`�0X�w����;S��h��QEX �"�E�-�1�?8�T���������b$�3��cqq�u�0���<+���|�9�^z���@��y�gh��54^dTQ�&ǚ��%�����q�Q�X揑�a�x��E,�J�m5{2ف�JUe��l���P��k�y���Lw,Zt;�VF�n�q�;��DQ��*&�Ŋe�@{:%"*�êl5�:��FC7�:=5cʭ�y���hqP �f��{�aM	�N�9
��^`ī`��ln���K�zf:XqN���<��ʅ9�z����s��L���V�X$5�U]`�Z����=�F��b, ���=qf��Bq����U��hu����7$*[}R���`'��H�~�W��.��"s��hz�cm�"��$>����Z�ʻ��G���n/p�޽�a\Y��S�o��VD'�Ri|E�:��$)}���ҽ*��V�LK�Ʒ���o&00�(�1����/�M�
�K���\<^t{,i)��� ����)u w	>��G# ���9�������R�RK�8E��]�GP�8�É��=1� �W�l��.M,J?�/��g#�/ib\O p�Sk0�AO_dQ�/��_ӣM�j�8�Q	X���SH"�-�oP�X���Y��}�~�2H_�,��v��2�qg|2XƓ+�R�1�ȕ��
9	raK�3���P��g 9����*u�K@##pu X������0��\��_&;�5�!=q6�P2Y ���Q�G��#���N�Y@Υ��� $@��aXا����}��vm�T�Do_X*9M`�nݻ�XL>�MT[	KNKwP�x�yC���y
V�N��y�B@|uf���[���HfG� q$R�
v
��?U�[���q,��}�8nj&�#�'	�E���%��E��2z�qF��R��R��%��!�x_9�����g�h��P�� <2�grK���//�Y���OJ����wl��S����*�;���6>�u�,�j��%�nm��� �d�Ba�E�(P� .�m�[L�i��LF]szR!�a�� ;��U����9���q�����]<���r���o�um�%&]Z�|�oz��;S�>z)�;���m,�[$��,/�C.�\t{B��kvZy�
�&X��*�6�L�pܘ�V�؜�9����1m����G�O�+%لqݍ
z����D�U%1P"�f�6�b�G�����շ?�    ���#��)~�)�m��ʸ-�h�|�r�ߋ����������V��@���^���E�D����{��~9Ƒ+]|���z ݥP�Ąl�񥈈8�9pY،���+X��M�gao�Wꎱ�ĵ��r�L����/�1/�F*�y���J�v���f��b�a�^����~[�o%V	�s�"��ߡ�s��f48���	i:��Ж��{�"�U���q��*ou�uZC�֙�s��=�j���(d������1�<���[)àIj '޷s-�:��ŭ���K.��� ���sgvd�8�G�i�#'w������e0���oa��~'ܿz�0ȳ�5 {���)�h��!q�9aK�m���b��fUt�Ke���	h�_JӀ���!�OC~���6Ť���CB�n�_!�%��@���{F�?]R�S�y'>q�� �*�z��47��A�u^�P#dXtA��U޹22�����Oe�}���܉�9UO�~MH.�oAd��d��{&xȋP:�̠��E����`�������xn�22�����x=|��� ߸G�uU�t���x�Sj1Pð�y��|x۩��]߽so&�1�p�D �g��!����88>w����P�[�����3�%&SI�Rݤ��K��Z�j�X� `��;�S��@�ܥ>u�'+e��ȧj�I�di?�)�������Ļ��@ ���:2B�� �_/�dj�0��w��3�Q$ �$�D�FK�
�${$9�awd9�MOr^�5LD!Q��}�?D	�s��w��[��y'X>rķ4f�t <A��dM1-��������gȳ�*r.��x���C�ޡ�wg�k�dƇ_���B ���Ss�3G�9����փX���Gj���KzRA.���W7[�'�ygE���E_(%�M�y����ZNʢ�B!̙>�j,�5la�7f�5�p�ۧ���+f�h
�:2����Q5l[Υ�@���?�`����ܾ���s�y�hV�1V/�"a�P	�G ���n�P�r":D.�l(o��/æ��ځk<���v��BƦ�B3�3�}c�*}����Ol&覆���\^�cơ^R����;����-^����H�IX�`O�;��IڰM�f%��!��IC��)�J?6x��sw�\�}7�c�R	{RQ�#ZK��(��o�!�)9/�X��).�s�w�A# mv��g�V�� �D�����C{��q&�|��t��`�M=&[M��ber=v�R�B��(�x�����%�`H���z�a��)�0w4B��Qœp�����R�~D�=`�����y��.nV�@u�Y�6�2%�rD�=L�z$�����gl�&!Gv�����6�	I�5���� �g��Sؔ��m���$�,]s��H\k����.4���6͌Cg���v~I����-��]��J��b<��n�җ%��i��Ѷ�Y���8��8�f�z�����|ٔi7�5q���Ǉ��`'�?徻�{2�Y)�+��$�: �h=��=b��J~ܐ��F4�M��ɧ�q)���X)_�.]��uL�v0��(I�+�(�92���[{����$����ʝ�*�͑��uhޞ,���L��������GȔ�&fO���N��P�ًI2�b��|�ײ�a��/��XX�.�HC��O�湸�
���/T�k�l�r}�{2��;й��Е�S��uI�KU?[����~��P�i��j���Hnq� o�vT���nu��|�B��Y�>h��q\�p=Yx'�^RzP�r�DK2�����LRZ�$_�S2L W��R;xe{��t�Jl(N�v(��hOh�8�ZnH%�yz�P���P�ͽ;�&��o�Q���x�Z-1���SyGڀ�%%�Q���^m ��f`{�8�hJ<�{1P���*9F�H�e�SnJ�)�%�N�n7��9�ex���[:��Ռ&e2/��f�i��m�� �������4$�)8�j�{��r^c%���c���
�%LQ	���TA�"\){�}!M�b�ܩZ�#mO
Ig�$�v{`�]�2����c4���x��m-$��"��](ގv{�9�ZR'�&i|�u�!�tm}�J�	w�U��{�m�4Aj#>�%��ʄ��&�$<�
�R�TJ :-��RĒޘX�{ڝ�uw)h	����`�T��L�/�4{����!�F�~�Kj��z2��YY��y�Z(�ן�DޖR��VatnN��{aqG�Kӕr�a�S�hYc�X�#�kpi�J�i����>��#VY�[x/T�p�kw >��:}��0�����r���{'&�,�rO*�25����S{ƪ��e`R\��`��K��]����7yWK.|
bU�bh��P�R�t�c�ت#>� +N~����K��v8{��e��HPll�t���vX�Y��1�Lh��.<�HiX��.�T��U�x9K�G�B�[��:%S�����sA
4-�ۿ5�]�z����I��>���j�P��������*�f0�pg�1��0�$2�}�O�=�I?��x�qQ�� >��Cq]g�����]V�#�=��N�肂���G݊/���{*�ƪBF�]����1�P�ЉF��<E���7������kq�Yfx.Z#E�+�7'�1�ᣋ3:3߲0�L+��o/Ƥ&���ؾ��:�z5��Ǽ�ɻ�]�=,�P���,���0�����P��Z�I�ʛ�� �6̫��>(���tK���Q]�H��(tz���T�/��r�0�v�b\��9@po��X�)�?�D���tc����D�S�j� ��{���}=���"�t�W9��>��]z��d ��ܸg~���(�XWP�K%���ݠ�$ o����RJ(V���3�|��̠��B��X��8c�]�� Y�d�|�FHL���-P��*Q�JlH����ҋ^�G��J,���Ž�1�c�Ceé�JqX/�E>M�B�� 6 1�.)l�加&�������K��ϩ �?�ܻ�C٤Zxt�!�U��:�Ul4%-Mi����@��{��?��D�x��5��G^J����QRh���̼�r'��*A>z�(��'���-wz�ͣd-`t0�]�����4a�=�b"4%W��zi���<��c�g�e$����I�O\ښU�	=ҽ1!	¯����}�F��K^��:�Z������ In(@]؛���5-�4!��~��3:P��a:z7�L����G\XW.��� ��d{E���l�M�}[�����+�Z�<[����&�2yLMdR�PGB��m}�.ص�.R9�X�(Ƞa;�8�O�	'�J�
���E+v�� ��א+�Z7L㱍k�$^�#/�5�F�*�� q(��&�Kԯ�$�~��g箞)s�j�b��Б$�Ɉ���Zr�Á��z�^���E�߇�G۷�����j�z R�f���
�5�Ue���#!9w^�x7i��^�@2��ڣ&�j|3C���N��a\�B�e}��ə�¤)K�� ߌ(�Gm��%����#z#F��bz�nq�b_����kM.ٳ�N��Q��lfט���!{�?��	W�-��?�I���>�#�Rg+�,k'ڣ�̩����
V%c�h��:�0zhM�+V�U�#K] ��Ƞ�ćo�B�?������D��<xH#���l_*��0&"�e��:��:�&�k��ȓ�	����;�MԊ;�����:��g�u��2�mM\k"�>��L����7�??q7'�(�iJ��3���ˋ�sa��Apq��)���l�?%�f���C:M��e�m�+3U� }[(�_y��d� �
hy7�B�ϵ��( ����B\2��+��	�Q���ޝz0�~�r$^5j!�1�(ν*�s�i5�{�F٪����@|Ui���6*��7+�LY_Z�h�Z�I�JM�g��?KY�~,���؉}fVK��-y�S!�W�P2�.b�S��
?/^��J	�i]��+��,}��G������Nϋ�ׁ("R��ؑ,���t$��ł�2w����]�/�B�Z2A�՗i�\�̓�W�o����Y��ր�`��.�N��    *�c�M��8a��P����hM��s^��K+��a9��L�x��Q�ڈ�姫@��l�QOr�$L�3�u�ѭ�Z�i��<|􃂣��m �!��&̺b��X�c^��.9f�N��)ޭ��P(O�W��ꭄ$��*�Ir&h jνcU����T��2I�o���N���ƳM��q՚� �b���i ���}oM�S|pǼ|cĪy%.���5���ʆ=��1 pY��J�.��qm�3~�5��
F�>R;�JW^�����fw�6I���T��u�@�;�=ozk����l�,�?�� R�3yU�_�A%f��p�ZA\� �Z��`�X��	@�c�m��f�]�wW7G�Wl����71v��t�æ5�VB�6���F��� ���h�K�d�����sNO�L��¦��@�+D�Ϸ���4+��a�É��$C�Q��L�M&�nh/]�"w:@�%�6�z���t��y���k�u������pg�@-��
����^�o"�L�K���� ����	��Y$'��u�k���O,�2;��r��6u
d��F��FgXJ�R��P3��J�E�x�hCKX�H����a%�[���I�[�;�6I�}T�;[�� �_� c9y')�x�nG��^�r��&�ڵ��nW�?@���W��H���L�ƽ�E�B�y]��k�����FĀ��}^���$�S\��S���۞պ��p���"[B{�#���>�[�Ų>��(]�����D6��ع�5�U�m4Ah��Z'��DN�BĊ~)��t�ń��� �VR��ګ�/ݾr��,�P3Hy�@��g5�}W?�z����y6^�xczM��*p�3�U�V�s��ܝ�O58	��r��z"0�ZY��K�M	��1���7 �k[Sk펮�;�vꇻ�@S�I�eg+�8F��u�)Dz�~۞�u-S�4�����6i����B��DǢ���\�=U��������ļNo`M/)7I��ou�К:�4��w��YSך�p�܋L�ߴ���.hj��N��Q7�J0���7�)��y��`�N���r������F�(�-q�jZV,�1���81��zK�>M"e`�r�O(��k�.��ӮО�}���9׬j��J�*�1u�8��4�8^�Wy'!2uKUd������,���7A�[�ROoP&�U�7����ŔM�5E=n�
W�H(Z����8�L>�����S���M��{��i����2We��ƽR��MPr�w��>��)�]d����L��k~��v-B��#k"�<�	�⤔>!��L���bn_B�kPs%I��0�+Wu�5�,����7�����A��.e���^�AϢRVkh�7u�
���`aз�����7�WX``��΁�`�cOJ�����`�?��K�0�D��F�N��`�V3KS���R5�~�W�a3?ؾ��x*�����,����a�"�bB����jα��Y�T�#�<oo�;�6(L���2�fx���7��hŢ��Z�R̆�ηo��c�%Z��Z^7%�vAɷf,|�ЯF�p��&Vۮ�\I��ΰ�Il.����:E���)Tz��^��<K���ܕ.�ΰO2��^U�K<]K�1t�(P�Z��-�u�m
��=+Ҟyu`�C�M����ZN�캱�Y6o��K��kiSp��pH@BId��W��O��	�m��Qά���[e$Rb�H/n�17�I 9>j�x�9��jzCl_���q�Qy]��V)���:#��n �Vx]RX4(q��xb*��1L���m8�g���M$;�S����z��Q�;�:K�h�Z��ģ�$ �ߞOT�b�1�vE��~�gмdÁܼ��X����2_������L�Z�N=�3��s(SN��������Q(+b(��"���W.�z�ٹl�Ci�jӢ˄�I�a-���M|b~��j��.��چR�$]�6J��7��ƃ�����L5���`%�L�'3�}�A�"�n�z���3�����j���̴����J+����?�I�����$x�C]PPo(y]�ש�Jk���f�SO*(�R@��6�S�t'�m�`hZ�K��@��A�d�ɳΘ�@s��n��)�%%��&�����{��$˜� �WUtŀTy�է$�~��F�Jٹ�9"��4�"~,�����5��Q%�]�®�R���}8�A������m���T��0@����K�n�-��ZM.߅`]E�N���$\�۰鶀щ3u�z�F#54��PZ��1�b�/�C�0���-����$:
��n�ϩ@)S�K����^	��'�IK�z[���i"Ǳ�o�&2���+�ֱ��l�3��+nn��>:�Q�2m)a�H����p�ɱ9
=�� ��X�Kz��m�R�rX��eK�(ۋ�Ѷ.�s����\�/=̕��m�z�ZbRw�8�Kq(�9�6�{���U��Du~n�m�EY,���m����g6��)b�}}GS"J6�yB��ٷƇ�qަ� ������\K��t�n{`A=�Ւ�b�J��䦰y�d �tW�y����N�o��K��Q]| E{d��t�L��n�i1���1Z��"�K����\k?��H7#4�d�t;-L�+���#�ʏU�](�60��)����V�e�M���҄9�-w;K�`~��Y���u���5��H{��_R�2V��,�R�wf���c�-���oUȺ:irܶ����� ��M����3�h�'U7Tj�d�9kq�z�D��(�(����Cv�ԥZ��)܁���s�W��J��sw7"�G�ot��YE�ʬ�n0`q�-�q�⥖Rs�w�8z�7Y��L��L��=WVBUv^��+htHc�L �&�� Ō�$��M�.ӢKM�՚���@�x�S�D�p�L�uԂ� 'l��\�&�a
 ��20aB{e�@I����\R�1�pg�	���2%)�A�G
M���ee֬.W��]MO��d��q��9�[�C�B���x��UaL��hz�U��\���m�G� �Ɠ�[�s��Y�d�Lw|����:gM�$)��L�\e�&H$u���p�7=S��UĨ����d�sNܻ��go�[ӇH���̇	��m�����e�=�p���U	�i*~�����/�nNk�����`��K���5��gs���F�̾qo��ڪ4��5:�l���{��&&�C��s����
���9�u����И(]|�G<���s�hm*Oe��&��]~Q�4g1���������Uym'Zd���A���a��ؿ�Za�������L�OR�5��Ly�o�臀��=kvIt��߷fc\	�z�����0������:z�ԋ H��Q�C�s���K�.g���sƾ3g�H͊+�s��:e$�|6������&�=��07gթ��΄�a X9ә��GN9��Ƈ�H�����k�l8�r �ʋW�e.	Y��(ٯ�A��]�c�a�D
�WTn�A��� �ި�Q�Z݇tj!zB�_��#�e��[{������	Lޑ����P���|��Y���{��,��,i5̀���+̍
�g @�5�$5���X���N�5 fy��e��Q<#�[p��P�� i�2�*��c�)��]i���Z�F$3b�;P��=6V�k�f�M�)Me�g�8�����&��)��r���qwh]27s�����{���������1=k�tҺ�	�nI�_w�]��) �����ˍ>w�ˀ6V�`8������o��B͵��p,ʓ� &޻�$��f�ڝ�g/��7�d��ME��i)�A<W?0P��+e����ZT�Ԗ����^ץ�����pz���L2�������k��ɔ���9<`.�A��	��0��.,g+�Y��'s�!vB[�$�,N `i���/�;L4<��M��G�vĉ@��F>nVO���eHi˙_����'ػ# E�#7̠sPW2�A��I1��6�M�Y){�,�Qe�wG@����ٮ;v���9�2K�աY���sB����y��q\�S�,Brh�/ 
  ٳ;qh�9�0���;�Y�v�k6�ןlbʆ0{�[?՗z�f~>i�.��7\ވeb��S�5����.�3]wz͎5���AH�D�s�c��I��s����X�ъ�^��Q���J;X��jTk?�6籐�*Y�K�դ���g��^�R6M���|J;��g�;*�k0u,��g1(m>�lo*��~ڬ*3�ܬ^sH"S��~X�#}B�����k������tr����������i�`��Mn>��#��Z �s�0���\k���j.����C����?�.��g����� �m�tgo����NhsޒRp��7j���@jn��>��|lTd�G-2fzf z��n�5#U`ʵ�����^�qo��>jGS�jh�M��iCR�mGR x\��'�qe�>a�'u�y��P�F��
�����R	<���A��DY�|�k �������,���Xx(�\d��{m�%]eW�18tO�*�[��^$�1ZV���^g��l%+}�vL}3�2�mS4n�&�K�a�}$_���|qg��k�\�3|������v�6p�|��ZI�|y"��{����>�_���^P'��	7x���k9���C�9K���[��LP`g0���0B�AmDj�O'�T`&՛*,5�R����v��49y.�?�q�jx)~]P/�5��N�S>I`�����A�.��:������}���4�J�9)���B���7?��cH���ܩs:S4��,u�q���rWu�r��^&��z m��ۓ&\��m-��u��I�~���T���/��:��������Z�nķn�U�]����P /�E�����	�v��k��6�O�1D�β���z)����i� T�۲>���Ͼovf1�ɛ1؇T{�6��҅�
5��6��Z��1Q��4V�o�u�:��&z���	����A�b�_���?�����ܳ�n�j�*J�z�v�KW��k��fPu�؞|*O�7H�t�l��D�B�����ū�`�:�*�I�E�;�T��}L=�_��+��}?��X�}>"n�&ݜ���7��q/�o��^��kZ�\����#�%�g[�/���/m��ӏU
�L*�ȣҢ�pǹm���{��.ͣ�MYG�!�<0��oa���u�qj�3@�Y��!p�c������[�^�̣J5��_���?�+w{]~�N�q�{%��� 2u��Z��w��Z���/|���|�#�%+�7����|�d��*G���Vs�I���'nJft��D����ϗ�gJ.�l����?K��~     