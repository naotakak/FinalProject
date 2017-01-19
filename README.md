<h3>summerRise : A summary tool</h3>
  summerRise is an article summary tool that can summarize an article from a text file or a URL.
  The article is mainly summarized by taking a wordCount and finding common words throughout the article.
  In addition, bias is added for sentences based on its place in the article i.e. sentences at the beginning
  and end, as well as sentences that contain words that are found in the title. 
  Based on this, a score is calculated for each sentence in the article. The sentences with the highest
  scores are added to the final summary.
  The URL is fed through the boilerpipe API, and then content is fetched from the resulting plain text webpage.
<h5>Added since demo</h5>
  We added a bias for sentence location and for common words in the title. We also added some grammar fixes for 
  initialed names and titles (Mr., Mrs., etc.) which would break the sentence splitting otherwise. We also tried
  to allow for summary of a article from a link, which is slightly broken due to curly quotes and special characters.
  <br>
  There are some test articles in the FinalProject repo - these should be formatted correctly with the title being the
  first line, and each sentence ending in punctuation. Paragraphs should be separated by "\n\n".
<h5>Working Features</h5>
  <ul>
    <li>Summarize an article from a local text file</li>
    <li>Specify the number of sentences in the final summary</li>
    <li>SORT OF: Summarize an article from a specified URL, using the boilerpipe API (See unresolved bugs)
    </li>
  </ul>
<h5>Unresolved Bugs</h5>
  <ul>
    <li>loadHTML can be too fast for boilerpipe API and might not adding all the content on the webpage</li>
    <li>Problems with replacing of curved quotation marks with regular plain quotation marks, which is
    leading to sentence splitting being broken on webpages using those quotations.</li>
  </ul>
<h5>How to compile</h5>
    javac summerRise.java
<h5>How to run code</h5>
    java summerRise [url/filename] [number of sentences in final summary] ["url" if url]
