#######
# TUNE OPTIONS
#######
GGML_TUNE_DEFAULT_PKGCONF ?= " \
    cpu-repack \
    ${@bb.utils.contains('TUNE_CCARGS', '-msse4.2', 'sse42', '', d)} \
    ${@bb.utils.contains('TUNE_FEATURES', 'x86-64-v3', 'sse42 avx avx2 fma f16c', '', d)} \
    \
    lasx \
    lsx \
    rvv \
    rv-zfh \
    rv-zvfh \
    rv-zicbop \
    rv-zihintpause \
"

# option(GGML_CPU_HBM          "ggml: use memkind for CPU HBM" OFF)
PACKAGECONFIG[cpu-hbm] = " -DGGML_CPU_HBM=ON, -DGGML_CPU_HBM=OFF, "

# option(GGML_CPU_REPACK       "ggml: use runtime weight conversion of Q4_0 to Q4_X_X" ON)
PACKAGECONFIG[cpu-repack] = " -DGGML_CPU_REPACK=ON, -DGGML_CPU_REPACK=OFF, "

# option(GGML_CPU_KLEIDIAI     "ggml: use KleidiAI optimized kernels if applicable" OFF)
PACKAGECONFIG[cpu-kleidiai] = " -DGGML_CPU_KLEIDIAI=ON, -DGGML_CPU_KLEIDIAI=OFF, "

# option(GGML_SSE42            "ggml: enable SSE 4.2"          ${INS_ENB})
PACKAGECONFIG[sse42] = " -DGGML_SSE42=ON, -DGGML_SSE42=OFF, "

# option(GGML_AVX              "ggml: enable AVX"              ${INS_ENB})
PACKAGECONFIG[avx] = " -DGGML_AVX=ON, -DGGML_AVX=OFF, "

# option(GGML_AVX_VNNI         "ggml: enable AVX-VNNI"         OFF)
PACKAGECONFIG[avx-vnni] = " -DGGML_AVX_VNNI=ON, -DGGML_AVX_VNNI=OFF, "

# option(GGML_AVX2             "ggml: enable AVX2"             ${INS_ENB})
PACKAGECONFIG[avx2] = " -DGGML_AVX2=ON, -DGGML_AVX2=OFF, "

# option(GGML_BMI2             "ggml: enable BMI2"             ${INS_ENB})
PACKAGECONFIG[bmi2] = " -DGGML_BMI2=ON, -DGGML_BMI2=OFF, "

# option(GGML_AVX512           "ggml: enable AVX512F"          OFF)
PACKAGECONFIG[avx512] = " -DGGML_AVX512=ON, -DGGML_AVX512=OFF, "

# option(GGML_AVX512_VBMI      "ggml: enable AVX512-VBMI"      OFF)
PACKAGECONFIG[avx512-vbmi] = " -DGGML_AVX512_VBMI=ON, -DGGML_AVX512_VBMI=OFF, "

# option(GGML_AVX512_VNNI      "ggml: enable AVX512-VNNI"      OFF)
PACKAGECONFIG[avx512-vnni] = " -DGGML_AVX512_VNNI=ON, -DGGML_AVX512_VNNI=OFF, "

# option(GGML_AVX512_BF16      "ggml: enable AVX512-BF16"      OFF)
PACKAGECONFIG[avx512-bf16] = " -DGGML_AVX512_BF16=ON, -DGGML_AVX512_BF16=OFF, "

# option(GGML_FMA          "ggml: enable FMA"              ${INS_ENB})
PACKAGECONFIG[fma] = " -DGGML_FMA=ON, -DGGML_FMA=OFF, "

# option(GGML_F16C         "ggml: enable F16C"             ${INS_ENB})
PACKAGECONFIG[f16c] = " -DGGML_F16C=ON, -DGGML_F16C=OFF, "

# option(GGML_AMX_TILE     "ggml: enable AMX-TILE"         OFF)
PACKAGECONFIG[amx-tile] = " -DGGML_AMX_TILE=ON, -DGGML_AMX_TILE=OFF, "

# option(GGML_AMX_INT8     "ggml: enable AMX-INT8"         OFF)
PACKAGECONFIG[amx-int8] = " -DGGML_AMX_INT8=ON, -DGGML_AMX_INT8=OFF, "

# option(GGML_AMX_BF16     "ggml: enable AMX-BF16"         OFF)
PACKAGECONFIG[amx-bf16] = " -DGGML_AMX_BF16=ON, -DGGML_AMX_BF16=OFF, "

# option(GGML_LASX             "ggml: enable lasx"              ON)
PACKAGECONFIG[lasx] = " -DGGML_LASX=ON, -DGGML_LASX=OFF, "

# option(GGML_LSX              "ggml: enable lsx"               ON)
PACKAGECONFIG[lsx] = " -DGGML_LSX=ON, -DGGML_LSX=OFF, "

# option(GGML_RVV              "ggml: enable rvv"               ON)
PACKAGECONFIG[rvv] = " -DGGML_RVV=ON, -DGGML_RVV=OFF, "


# RISCV CPU OPTIONS
# option(GGML_RV_ZFH           "ggml: enable riscv zfh"         ON)
PACKAGECONFIG[rv-zfh] = " -DGGML_RV_ZFH=ON, -DGGML_RV_ZFH=OFF, "

# option(GGML_RV_ZVFH          "ggml: enable riscv zvfh"        ON)
PACKAGECONFIG[rv-zvfh] = " -DGGML_RV_ZVFH=ON, -DGGML_RV_ZVFH=OFF, "

# option(GGML_RV_ZICBOP        "ggml: enable riscv zicbop"      ON)
PACKAGECONFIG[rv-zicbop] = " -DGGML_RV_ZICBOP=ON, -DGGML_RV_ZICBOP=OFF, "

# option(GGML_RV_ZIHINTPAUSE   "ggml: enable riscv zihintpause" ON)
PACKAGECONFIG[rv-zihintpause] = " -DGGML_RV_ZIHINTPAUSE=ON, -DGGML_RV_ZIHINTPAUSE=OFF, "

# option(GGML_RV_ZVFBFWMA      "ggml: enable riscv zvfbfwma"    OFF)
PACKAGECONFIG[rv-zvfbfwma] = " -DGGML_RV_ZVFBFWMA=ON, -DGGML_RV_ZVFBFWMA=OFF, "

# option(GGML_XTHEADVECTOR     "ggml: enable xtheadvector"      OFF)
PACKAGECONFIG[xtheadvector] = " -DGGML_XTHEADVECTOR=ON, -DGGML_XTHEADVECTOR=OFF, "

########
# Unsupported
########
# option(GGML_VXE              "ggml: enable vxe"               ${GGML_NATIVE}) we are never native so .....
# PACKAGECONFIG[vxe] = " -DGGML_VXE=ON, -DGGML_VXE=OFF,
