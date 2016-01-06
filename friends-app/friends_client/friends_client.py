import requests
import json
from friend import Friend

base_url = 'http://localhost:8080/friends-app'

def test_service():
      url = base_url + '/testservice'
      try:
            res = requests.get(url)
            print 'test_service()-> ', res.content
            return res.status_code
      except BaseException as e:
            print str(e)

def get_friend_details(url):
      res = requests.get(url)
      print '*** Querying ', url
      #convert the string to json
      d = json.loads(res.content, object_hook = Friend.toJSON_hook)
      print d

def get_all():
      url = base_url + '/getall'

      print '*** Querying ', url
      
      try:
            res = requests.get(url)
      except BaseException as e:
            print str(e)
      else:
            if res.status_code == 200:
                  d = json.loads(res.content) # json to list
                  # iterating the list, each item in the list is a dict
                  for item in d:
                        get_friend_details(item)

def add_friend(f):
      url = base_url + '/add'

      print '*** Adding friend', f

      try:
            # NOTE: json.dumps(...) is not used because we need to send the json data as dict and not as str
            res = requests.post(url, json = f.__dict__, \
                                headers = {'content-type': 'application/json', 'accept': 'application/json'})
      except BaseException as e:
            print str(e)
      else:
            if res.status_code == 200:
                  print 'Sucessfully added friend.'
            else:
                  print 'Failed to add friend.', res.content
      
if __name__ == '__main__':
      status_code = test_service()
      if status_code == 200:
            # add a friend
            f = Friend('Python Client', 'Roxy Blacker', 'freaked')
            add_friend(f)

            # get all friends and explore the URIs
            get_all()
      else:
            print '*** Service is down.'
