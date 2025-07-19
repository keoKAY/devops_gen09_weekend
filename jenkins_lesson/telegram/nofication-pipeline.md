## NOTE 
> for working with telegram notification 



```groovy

script{
    def token=""
    def chatId=""
    sh 'curl -s -X POST https://api.telegram.org/bot${token}/sendMessage -d chat_id="${chatId}" -d text="Hello from Jenkins !"' 
}

```