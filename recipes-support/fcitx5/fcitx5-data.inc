DATA_URL = "https://download.fcitx-im.org/data"

EN_DICT_URL = "${DATA_URL}/en_dict-20121020.tar.gz;name=en-dict;unpack=0;subdir=git/src/modules/spell;"
SRC_URI[en-dict.sha256sum] = "c44a5d7847925eea9e4d2d04748d442cd28dd9299a0b572ef7d91eac4f5a6ceb"

## LIBIME
LM_SC_ARPA_URL="${DATA_URL}/lm_sc.arpa-20260629.tar.zst;name=lm;unpack=0;subdir=git/data;"
SRC_URI[lm.sha256sum] = "06808333b9173e5374cf2cb5afc12d08f5625bf9abb536489cac376fc05f2e7f"

TABLE_URL = "${DATA_URL}/table-20240108.tar.zst;name=table;unpack=0;subdir=git/data;"
SRC_URI[table.sha256sum] = "3e9d87b04a393f131723472c8eaa860dd23c378a3d4f6a9005513b2a95b3614b"

DICT_URL = "${DATA_URL}/dict-20260907.tar.zst;name=dict;unpack=0;subdir=git/data;"
SRC_URI[dict.sha256sum] = "fb75a179065e690dfc4559ce1807cbaf4fbe4f0111a5005615be9f435e6b9d76"

## LIBIME JYUTPING
JYUTPING_DICT_URL = "${DATA_URL}/jyutping-dict-20180104.tar.xz;name=jyutping-dict;unpack=0;subdir=git/data;"
SRC_URI[jyutping-dict.sha256sum] = "e3a5b13edb8efa2f764245a3232f99ba7e7670e22b8cbe666a4fffa84b35f35b"

JYUTPING_MODEL_URL = "${DATA_URL}/jyutping-model-20180103.tar.xz;name=jyutping-model;unpack=0;subdir=git/data;"
SRC_URI[jyutping-model.sha256sum] = "4f07229e2080f0ee30ce51b016409f260af82a58dd406a01ea5981b59ca87071"

## FCITX5-CHINESE-ADDONS
PY_TABLE_URL = "${DATA_URL}//py_table-20121124.tar.gz;name=py-table;unpack=0;subdir=git/modules/pinyinhelper;"
SRC_URI[py-table.sha256sum] = "42146ac97de6c13d55f9e99ed873915f4c66739e9c11532a34556badf9792c04"

PY_STROKE_URL = "${DATA_URL}/py_stroke-20250329.tar.gz;name=py-stroke;unpack=0;subdir=git/modules/pinyinhelper;"
SRC_URI[py-stroke.sha256sum] = "c1a7ca7225d3614ab83353fc827503006a980447762018f53760425d7b5303a6"
