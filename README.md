<h3>The Hyve Test Assignment 2</h3>

<b>Author:</b> Bernd van der Veen
<br />
<b>Date:</b> 29/01/2015

<b>Requirements:</b>
- JDK 7 (http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
- Maven 3.2.5 (http://maven.apache.org/download.cgi)

<h3>Building</h3>
1. Clone repository into a directory
2. mvn clean install

The built jar (TheHyveTest-1.0.jar) can be found in target/

<h3>Running:</h3>
<pre>
1. Default non-trivial implementation  
> java -jar target/TheHyveTest-1.0.jar &lt; binaries/assignmentTestData.bin 2> binaries/assignmentTestData-NonTrivial.bin

Stdout: aabaabaa
</pre>

<pre>
2. Default trivial implementation
> SET USE_TRIVIAL_IMPLEMENTATION=1
> java -jar target/TheHyveTest-1.0.jar &lt; binaries/assignmentTestData.bin 2> binaries/assignmentTestData-Trivial.bin

Stdout: aabaabaa
</pre>
Invalid characters will be decoded / recoded as '?' (0x3F)

<h3>Errors</h3>
<pre>
Application will error and exit when:
- Invalid indicator char (first in pair)
- Invalid value char (second in pair) when encoding for a plain char (indicator = 0)
- Invalid offset repeat sequence indicator (larger than length of current decoded message)
- Invalid offset repeat value (larger than current length of decoded message)
</pre>

<h3>Test data</h3>
<br/>
<code>
final byte test[] = new byte[] { 
	(byte)0x30, 0x61, 
    (byte)0x31, 0x31,
    (byte)0x30, 0x62,
    (byte)0x33, 0x32,
    (byte)0x33, 0x33,
};
</code>
<br/>
Encodes for: aabaabaa
