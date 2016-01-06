import json
from json import JSONEncoder

class Friend(object):
      def __init__(self, name, profession, website):
            self.name = name
            self.profession = profession
            self.website = website

      def __str__(self):
            return self.name + '/' + self.profession + '/' + self.website
 
      # Object hook for the JSON Decoder
      @staticmethod
      def toJSON_hook(obj):
            if 'name' in obj and 'profession' in obj and 'website' in obj:
                  return Friend(obj['name'], obj['profession'], obj['website'])
            else:
                  raise TypeError('Invalid object. Object should be of type \'Friend\'.')

#JSON Encoder for the Friend class
class FriendJSONEncoder(JSONEncoder):
      def default(self, obj):
            if isinstance(obj, Friend):
                  return obj.__dict__
            else:
                  raise TypeError('Invalid object. Object should be of type \'Friend\'.')


# small test for the above classes
if __name__ == '__main__':
      friend = Friend('Prithviraj Bose', 'Independent Software Developer', 'www.prithvirajbose.com')
      friend_json = json.dumps(friend, cls = FriendJSONEncoder) # obj to JSON string
      print 'JSON Dump -> ', friend_json
      #reconstruct the obj again from the JSON string
      restored_friend = json.loads(friend_json, object_hook = Friend.toJSON_hook)
      print 'JSON Load -> ', restored_friend
