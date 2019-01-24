1)We have used Jsoup to crawl web pages from the internet.

2)We used Jsoup to extact text from HTML files. We have used 'split()' function to tokenize the text and convert each word into individual strings.

3)We have created an Inverted Index to store strings as our keys and the URL of web pages where the words occur as our values. We created a linked list
to store the list of URLs and used a trie to store the string keys.

We match the search keyword by looking up keys in our trie and display the corresponding value which gives the list of URLs of web pages containing that word.

4)We use Quicksort to rank the web pages according to the frequency of occurence of search word in each of the web pages.

