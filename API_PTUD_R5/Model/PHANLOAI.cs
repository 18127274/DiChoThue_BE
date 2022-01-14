using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
// <snippet_NewtonsoftJsonImport>
using Newtonsoft.Json;
// </snippet_NewtonsoftJsonImport>

namespace API_PTUD_R5.Model
{
    public class PHANLOAI
    {
        [BsonId]
        public long Id { get; set; }
        public string Ten { get; set; }
    }
}
