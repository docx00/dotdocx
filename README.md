# dotdocx
A barebones system for doing stuff with friends.

# How to set up
Currently, dotdocx only comes with a barebones command line interface. Don't be afraid though, it's really easy to use! Go ahead and download the latest release from the Releases tab and open the version for your operating system. If you are on a Mac/Unix/Linux system, you will need to install ffmpeg and ffplay.
### For Hosts
Go ahead and grab OBS or your favorite broadcasting software that supports RTMP. Now launch dotdocx. Select the first option for "Hosts" and type in your room name. The console will give you a URL to copy down and a stream key. Those who've streamed before should be familiar with the Address/Key system, but for those who aren't this the key is what differentiates your party from other parties. Once you have changed your stream address and key, go ahead and send the room name to all your friends and start streaming.
### For Viewers
This step is just as simple as being a host, if not a bit more. Run dotdocx, select the second option for "Viewers", and type in the room name your friend gave you. And if your host is streaming, the console should now fill with statistics about the stream.

# FFPlay Keyboard Shortcuts
For interacting with the stream viewing menu, [there are keyboard shortcuts](https://ffmpeg.org/ffplay.html#While-playing). Somne basic ones you should know are:

| Key | Function                           |
|-----|------------------------------------|
| f   | Puts the stream in fullscreen mode |
| m   | Mutes the stream                   |
| p   | Pauses the stream                  |
