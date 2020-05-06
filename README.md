# Java Library for Private Set Intersection (PSI)

## Requirements

* Linux
* Java 64-bit
* keep the library file (.so) in the working directory

## Test programm

Server: specify input file and port
```
LD_LIBRARY_PATH=$PWD java -jar ./psi1-0-1.jar Server Forthnet_CRM_Data.csv 8000
```

Client: specify input file, host and port
```
LD_LIBRARY_PATH=$PWD java -jar ./psi1-0-1.jar Client Bank_CRM_Data.csv 127.0.0.1 8000
```

This performs a set intersection of the first column of the two files. The output is written into the two files. First, a text file intersection_raw.txt and secondly into a html file intersection.html (both on the client side). Note that it only supports ZIP-codes so far, i.e. values up to 16 digits.

## Disclaimer

This code is provided as a experimental implementation for testing purposes and should not be used in a productive environment. We cannot guarantee security and correctness.

## Acknowledgements

This project uses several other projects as building blocks.

* The OT code is based on the public domain library [libOTe](https://github.com/osu-crypto/libOTe) by Peter Rindal.
* Elliptic Curve operations are implemented using [MIRACL](https://github.com/miracl/MIRACL).
* Some of the binary circuits are based on ones from [ABY](https://github.com/encryptogroup/ABY).
* The garbled circuit interface is inspired by [FlexSC](https://github.com/wangxiao1254/FlexSC).
* The used cuckoo filter implementation is [cuckoofilter](https://github.com/efficient/cuckoofilter).
* The implementation of LowMC is based on [Picnic](https://github.com/IAIK/Picnic).

