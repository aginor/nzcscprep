FROM ubuntu:23.04

RUN apt-get update && apt-get install -y \
    python3-pip \
    python3-dev \
    libssl-dev \
    libimage-exiftool-perl  \
    libexempi* \
    p7zip-full \
    foremost \
    steghide \
    libmagic1 \
    build-essential \
    ruby-dev \
    ruby-rubygems \
    file \
    less \
    curl \
    libcurl4-gnutls-dev \
    gdb \
      && rm -rf /var/lib/apt/lists/*


RUN python3 -m pip install --break-system-packages \
    stego-lsb \
    stegoveritas \
    xortool \
    Stegano \
    EmojiEncode

RUN gem install \
    zsteg

ENV DEBUGINFOD_URLS="https://debuginfod.ubuntu.com"
