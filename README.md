<h3>summerRise : A summary tool</h3>
  summerRise is an article summary tool that can summarize an article from a text file or a URL.
  The article is mainly summarized by taking a wordCount and finding common words throughout the article.
  In addition, bias is added for sentences based on its place in the article i.e. sentences at the beginning
  and end, as well as sentences that contain words that are found in the title. 
  Based on this, a score is calculated for each sentence in the article. The sentences with the highest
  scores are added to the final summary.
<h5>Working Features</h5>
  <ul>
    <li>Summarize an article from a local text file</li>
    <li>Specify the number of sentences in the final summary</li>
    <li>Summarize an article from a specified URL, using the boilerpipe API</li>
  </ul>
<h5>Unresolved Bugs</h5>
  <ul>
    <li>Sentence splitting slightly broken when using URL</li>
  </ul>
<h5>How to compile</h5>
    javac summerRise.java
<h5>How to run code</h5>
    java summerRise [url/filename] [number of sentences in final summary] ["url" if url]
