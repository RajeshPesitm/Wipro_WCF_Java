# Project Planning
https://share.google/aimode/0V5mFl3XsDtCrsTs2
https://share.google/aimode/36rTAHPYBnvEOVzop
https://share.google/aimode/wjgKcgRFJGfoB9WvP
https://share.google/aimode/5X3Mx6Lw9wWujbeQP
https://share.google/aimode/pQqefJmGUnRNwTLWP
https://share.google/aimode/mDiz8V40M5EjJ68cj
https://share.google/aimode/23HThWoLSOwZUF8KP
https://share.google/aimode/XwIUjUjmzUwUa9RBP
https://share.google/aimode/3iPtxbValgx1ylgUK



Technologies Used
```text
Excellent choices. Spring Boot, WebSockets, and H2 are the perfect trifecta for this application. WebSockets will allow you to instantly push new high bids to all connected participants' screens without making them refresh the page, while H2 eliminates the need to configure an external database during development.
```

### Fix needed next
```text
You found the exact root cause! Your network or internet provider is blocking or timing out when trying to download the WebSocket files from jsdelivr.net. Because SockJS fails to load, the JavaScript engine crashes immediately, preventing any connection from opening.Since we are running a Maven project, we don't need to rely on external internet download links. We can tell Maven to download the WebSocket libraries directly into your project's local directory and serve them locally from your server.Here is how you fix this so your app runs completely offline without needing external internet links:
```
https://share.google/aimode/H8uhrfPNkPu0M7GZp

