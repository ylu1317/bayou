#!/bin/bash
# docker run --rm -v $(pwd):/root -p 8888:8888 -it crac1017/elec677 /bin/bash -c "/opt/conda/bin/jupyter notebook --notebook-dir=/root --ip='*' --port=8888 --no-browser"
# docker run --rm -v $(pwd):/root -p 8888:8888 -it crac1017/tensorflow:cpu /bin/bash -c "jupyter notebook --notebook-dir=/root --ip='*' --port=8888 --no-browser"
# docker run --rm -p 6006:6006 -v $(pwd):/root -it crac1017/tensorflow:cpu /bin/bash
# nvidia-docker run --rm -p 6006:6006 -v $(pwd):/root -it crac1017/tensorflow:gpu /bin/bash
# docker run --rm -p 8888:8888 -v $(pwd):/root -it ubuntu /bin/bash
# docker run -p 8888:8888 -v $(pwd):/root -it ubuntu /bin/bash
# docker run --name bayou-yl64 --rm -v $(pwd):/root -p 8080:8080 -p 8081:8081 -p 8084:8084 -it bayou /bin/bash
# docker run --name bayou-yl64 --entrypoint "/bin/bash" -p 8080:8080 -p 8081:8081 -p 8084:8084 -it bayou
## docker run -v $(pwd):/root -p 8080:8080 -p 8081:8081 -p 8084:8084 -it ubuntu /bin/bash
# docker run --name bayou-yl64 --rm -v $(pwd):/root -p 8080:8080 -p 8081:8081 -p 8084:8084 -it crac1017/bayou /bin/bash
docker run --name bayou-yl64 -v $(pwd):/root -p 8080:8080 -p 8081:8081 -p 8084:8084 -it crac1017/bayou /bin/bash
# nvidia-docker run --rm -it -p 8888:8888 crac1017/tensorflow:gpu
# docker run --rm -v $(pwd):/root -p 8888:8888 -it crac1017/tensorflow:cpu /bin/bash 
