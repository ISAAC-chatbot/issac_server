#!/bin/bash

# 1. 서버 타임존을 한국(KST)으로 변경
sudo ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
sudo bash -c "echo 'Asia/Seoul' > /etc/timezone"

# 2. 설정 확인
echo "Server timezone set to Asia/Seoul:"
date
