@RestController
@EnableCaching
public class HackerNewsController {
  
  private final RestTemplate restTemplate = new RestTemplate();
  
  @Cacheable("stories")
  @GetMapping("/top-stories")
  public List<Story> getTopStories() {
    // Get the top story IDs from the Hacker News API
    String url = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
    Integer[] storyIds = restTemplate.getForObject(url, Integer[].class);
    
    // Get the details of each story
    List<Story> stories = new ArrayList<>();
    for (int i = 0; i < 10 && i < storyIds.length; i++) {
      String storyUrl = "https://hacker-news.firebaseio.com/v0/item/" + storyIds[i] + ".json?print=pretty";
      Story story = restTemplate.getForObject(storyUrl, Story.class);
      stories.add(story);
    }
    
    // Sort the stories by score
    stories.sort((s1, s2) -> s2.getScore() - s1.getScore());
    
    return stories;
  }
  
  @Cacheable("stories")
  @GetMapping("/past-stories")
  public List<Story> getPastStories() {
    // Return the cached stories
    return getTopStories();
  }
  
  @GetMapping("/comments/{storyId}")
  public List<Comment> getComments(@PathVariable int storyId) {
    // Get the story details from the Hacker News API
    String storyUrl = "https://hacker-news.firebaseio.com/v0/item/" + storyId + ".json?print=pretty";
    Story story = restTemplate.getForObject(storyUrl, Story.class);
    
    // Get the comment IDs for the story
    Integer[] commentIds = story.getKids();
    
    // Get the details of each comment
    List<Comment> comments = new ArrayList<>();
    for (int i = 0; i < 10 && i < commentIds.length; i++) {
      String commentUrl = "https://hacker-news.firebaseio.com/v0/item/" + commentIds[i] + ".json?print=pretty";
      Comment comment = restTemplate.getForObject(commentUrl, Comment.class);
      comments.add(comment);
    }
    
    // Sort the comments by number of child comments
    comments.sort((c1, c2) -> {
      Integer numChildComments1 = c1.getKids() != null ? c1.getKids().length : 0;
      Integer numChildComments2 = c2.getKids() != null ? c2.getKids().length : 0;
      return numChildComments2 - numChildComments1;
    });
    
    return comments;
  }
  
  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(Arrays.asList(
      new ConcurrentMapCache("stories")
    ));
    return cacheManager;
  }
}
