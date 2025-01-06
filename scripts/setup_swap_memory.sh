#!/bin/bash

# 1. Swap 파일 생성 및 설정
sudo dd if=/dev/zero of=/swapfile bs=128M count=16
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile

# 2. Swap 활성화 확인
sudo swapon -s

# 3. Swap 메모리 자동 활성화 설정
sudo bash -c "echo '/swapfile swap swap defaults 0 0' >> /etc/fstab"

# 설정 확인
echo "Swap file added to /etc/fstab:"
tail -n 1 /etc/fstab
