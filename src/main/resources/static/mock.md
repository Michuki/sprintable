FORMAT: 1A
HOST: http://polls.apiblueprint.org/

# test

Polls is a simple API allowing consumers to view polls and vote in them.

## Questions Collection [/questions]

### List All Questions [GET]

+ Response 200 (application/json)

        {
            "collection" :
            {
                "version" : "1.0",
                "href" : "http://www.youtypeitwepostit.com/api/",
                
                "items" : 
                [
            {
                "href": "http://www.youtypeitwepostit.com/api/23572232108563185",
                "data": [
                    {
                        "name": "text",
                        "value": "Nathan"
                    },
                    {
                        "name": "date_posted",
                        "value": "2016-04-07T06:28:28.032Z"
                    }
                ]
            },
            {
                "href": "http://www.youtypeitwepostit.com/api/613856331910938",
                "data": [
                    {
                        "name": "text",
                        "value": "Squidly!"
                    },
                    {
                        "name": "date_posted",
                        "value": "2013-03-28T21:51:08.406Z"
                    }
                ]
            },
            {
                "href": "http://www.youtypeitwepostit.com/api/6091476338915527",
                "data": [
                    {
                        "name": "text",
                        "value": "test1"
                    },
                    {
                        "name": "date_posted",
                        "value": "2016-04-07T09:40:20.501Z"
                    }
                ]
            },
            {
                "href": "http://www.youtypeitwepostit.com/api/33522630389779806",
                "data": [
                    {
                        "name": "text",
                        "value": "test"
                    },
                    {
                        "name": "date_posted",
                        "value": "2016-04-07T07:36:02.866Z"
                    }
                ]
            },
            {
                "href": "http://www.youtypeitwepostit.com/api/7393070771358907",
                "data": [
                    {
                        "name": "text",
                        "value": "123"
                    },
                    {
                        "name": "date_posted",
                        "value": "2016-04-07T08:36:02.482Z"
                    }
                ]
            },
            {
                "href": "http://www.youtypeitwepostit.com/api/48071847343817353",
                "data": [
                    {
                        "name": "text",
                        "value": "test"
                    },
                    {
                        "name": "date_posted",
                        "value": "2016-04-07T05:43:09.499Z"
                    }
                ]
            }
        ]
                ,

                "template" : {
                    "data" : [
                        {"prompt" : "Text of message", "name" : "text", "value" : ""}
                    ]
                }
            }
        }