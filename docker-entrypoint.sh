#!/bin/sh
set -e

# Oracle ADB wallet: cwallet.sso is binary and gets corrupted when pasted as a
# text secret file, so it is supplied base64-encoded in WALLET_CWALLET_B64.
# The text files (tnsnames.ora, sqlnet.ora, ojdbc.properties) can stay in
# WALLET_SOURCE_DIR (Render secret files, /etc/secrets).
if [ -n "$WALLET_CWALLET_B64" ]; then
  WALLET_TARGET="/tmp/wallet"
  mkdir -p "$WALLET_TARGET"
  for f in tnsnames.ora sqlnet.ora ojdbc.properties; do
    [ -f "${WALLET_SOURCE_DIR:-/etc/secrets}/$f" ] && cp "${WALLET_SOURCE_DIR:-/etc/secrets}/$f" "$WALLET_TARGET/$f"
  done
  printf '%s' "$WALLET_CWALLET_B64" | base64 -d > "$WALLET_TARGET/cwallet.sso"
  export WALLET_DIR="$WALLET_TARGET"
fi

exec java -XX:MaxRAMPercentage=75.0 -jar app.jar
